package com.curso.api.spring_securiy_course.service.auth;

import com.curso.api.spring_securiy_course.dto.RegisteredUser;
import com.curso.api.spring_securiy_course.dto.SaveUser;
import com.curso.api.spring_securiy_course.dto.auth.AuthenticationRequest;
import com.curso.api.spring_securiy_course.dto.auth.AuthenticationResponse;
import com.curso.api.spring_securiy_course.exception.ObjectNotFoundException;
import com.curso.api.spring_securiy_course.persistence.entity.User;
import com.curso.api.spring_securiy_course.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Service that registers one customer
 */
@Service
public class AuthenticationService {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Using both services, you will validate and registering a client, after that it will generate a JWT compact
     * @param newUser
     * @return
     */
    public RegisteredUser registerOneCustomer(@Valid SaveUser newUser) {
        User user = userService.registerOneCustomer(newUser);

        RegisteredUser userDto = new RegisteredUser();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setUsername(user.getUsername());
        userDto.setRole(user.getRole().getName());

        String jwt = jwtService.generateToken(user, generateExtraClaims(user));
        userDto.setJwt(jwt);

        return userDto;
    }

    /**
     * Generates claims
     * @param user
     * @return
     */
    private Map<String, Object> generateExtraClaims(User user){
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", user.getName());
        extraClaims.put("role", user.getRole().getName());
        extraClaims.put("authorities", user.getAuthorities());

        return extraClaims;
    }

    public AuthenticationResponse login(@Valid AuthenticationRequest authRequest) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                authRequest.getUsername(),
                authRequest.getPassword()
        );
        authenticationManager.authenticate(authentication);

        UserDetails user = userService.findOneByUsername(authRequest.getUsername()).get();

        String jwt = jwtService.generateToken(user, generateExtraClaims((User) user));

        AuthenticationResponse authRsp = new AuthenticationResponse();
        authRsp.setJwt(jwt);

        return authRsp;
    }

    public boolean validateToken(String jwt) {
        try{
            jwtService.extractUsername(jwt);
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

    }

    /**
     * We get session's user in short
     * @return
     */
    public User findLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        /// It's a way to instance
        if(auth instanceof UsernamePasswordAuthenticationToken authToken){
            String username = (String) authToken.getPrincipal();

            return userService.findOneByUsername(username)
                    .orElseThrow(() -> new ObjectNotFoundException("User not found. Username: " + username));
        }
        return null;
    }
}
