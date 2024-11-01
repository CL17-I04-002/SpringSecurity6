package com.curso.api.spring_securiy_course.config.security.authorization;

import com.curso.api.spring_securiy_course.exception.ObjectNotFoundException;
import com.curso.api.spring_securiy_course.persistence.entity.Operation;
import com.curso.api.spring_securiy_course.persistence.entity.User;
import com.curso.api.spring_securiy_course.persistence.repository.OperationRepository;
import com.curso.api.spring_securiy_course.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * This is our own Authorization implementation with public and private endpoints
 */
@Component
public class CustomAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {
    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private UserService userService;

    /**
     * we evaluate whether endpoint that you want to access is public or private, and if it has granted to access, so, it can go ahead
     * @param authentication the {@link Supplier} of the {@link Authentication} to check
     * @param requestContext the {@link T} object to check
     * @return
     */
    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication,
                                       RequestAuthorizationContext requestContext) {
        HttpServletRequest request = requestContext.getRequest();
        System.out.println(request.getRequestURL());
        System.out.println(request.getRequestURI());
        
        String url = extractUrl(request);
        String httpMethod = request.getMethod();
        // We validate public endpoints
        boolean isPublic = isPublic(url, httpMethod);
        if(isPublic) return new AuthorizationDecision(true);

        boolean isGranted = isGranted(url, httpMethod, authentication.get());

        return new AuthorizationDecision(isGranted);
        

    }

    private boolean isGranted(String url, String httpMethod, Authentication authentication) {
        if(authentication == null || !(authentication instanceof UsernamePasswordAuthenticationToken)){
            throw new AuthenticationCredentialsNotFoundException("User not logged in");
        }

        /// We get actual operation that user may have
        List<Operation> operations = obtainOperations(authentication);

        boolean isGranted = operations.stream().anyMatch(getOperationPredicate(url, httpMethod));

        System.out.println("IS GRANTED: " + isGranted);

        return isGranted;

    }

    /**
     * We go through the operations and the one that matches the route and the http method will return true (we compare them with the route that comes in the http request)
     * @param url
     * @param httpMethod
     * @return
     */
    private static Predicate<Operation> getOperationPredicate(String url, String httpMethod) {
        return operation -> {
            String basePath = operation.getModule().getBasePath();
            Pattern pattern = Pattern.compile(basePath.concat(operation.getPath()));
            Matcher matcher = pattern.matcher(url);

            return matcher.matches() && operation.getHttpMethod().equals(httpMethod);
        };
    }

    /**
     * We get Operations of actual user o principal
     * @param authentication
     * @return
     */
    private List<Operation> obtainOperations(Authentication authentication) {
        UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) authentication;
        String username = (String) authToken.getPrincipal();
        User user = userService.findOneByUsername(username).
                orElseThrow( () -> new ObjectNotFoundException("User not found. Username " + username));
        return user.getRole().getPermissions().stream()
                .map(grantedPermission -> grantedPermission.getOperation())
                .collect(Collectors.toList());
    }

    /**
     * We get actual endpoint and if it i'snt public, return false
     * @param url
     * @param httpMethod
     * @return
     */
    private boolean isPublic(String url, String httpMethod){
        List<Operation> publicAccessEndpoints = operationRepository
                .findByPublicAcces();
        boolean isPublic = publicAccessEndpoints.stream().anyMatch(getOperationPredicate(url, httpMethod));
        System.out.println("IS PUBLIC" + isPublic);

        return isPublic;
    }

    /**
     * We get URL en replace /api/v1 by string empty
     * @param request
     * @return
     */
    private String extractUrl(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        // /api/v1
        String url = request.getRequestURI();
        url = url.replace(contextPath, "");
        return url;
    }
}
