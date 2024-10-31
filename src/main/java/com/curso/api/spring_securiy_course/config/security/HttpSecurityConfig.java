package com.curso.api.spring_securiy_course.config.security;

import com.curso.api.spring_securiy_course.config.security.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
// It enables http security by method, usually comes in the controllers
@EnableMethodSecurity(prePostEnabled = true)
public class HttpSecurityConfig {
    @Autowired
    private AuthenticationProvider daoAuthProvider;
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    /**
     * Helps us to configure filters and securing the endpoints
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf( csrfConfig -> csrfConfig.disable() )
                .sessionManagement(sessMagConfig -> sessMagConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(daoAuthProvider)
                // We're gonna run jwtAuthenticationFilter before UsernamePasswordAuthenticationFilter
                // Filters have a sort, in this case UsernamePasswordAuthenticationFilter is 1900, but you can find them in the documentation at addFilterBefore
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                /*.authorizeHttpRequests( authReqConfig -> {

                    buildRequestMatchers(authReqConfig);
                })*/
                .build();
    }

    private static void buildRequestMatchers(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authReqConfig) {
    /*
    Products Endpoint Authorization
     */

       /* authReqConfig.requestMatchers(HttpMethod.GET, "/products")
                .hasAnyRole(Role.ADMINISTRATOR.name(), Role.ASSISTANT_ADMINISTRATOR.name());


        authReqConfig.requestMatchers(HttpMethod.GET, "/products/{productId}")
                        .hasAnyRole(Role.ADMINISTRATOR.name(), Role.ASSISTANT_ADMINISTRATOR.name());

        authReqConfig.requestMatchers(HttpMethod.POST, "/products")
                .hasRole(Role.ADMINISTRATOR.name());

        authReqConfig.requestMatchers(HttpMethod.PUT, "/products/{productId}")
                .hasAnyRole(Role.ADMINISTRATOR.name(), Role.ASSISTANT_ADMINISTRATOR.name());

        authReqConfig.requestMatchers(HttpMethod.PUT, "/products/{productId}/disabled")
                .hasRole(Role.ADMINISTRATOR.name());

                    *//*
                    Categories Endpoint Authorization
                     *//*

        authReqConfig.requestMatchers(HttpMethod.GET, "/categories")
                .hasAnyRole(Role.ADMINISTRATOR.name(), Role.ASSISTANT_ADMINISTRATOR.name());

        authReqConfig.requestMatchers(HttpMethod.GET, "/categories/{categoryId}")
                .hasAnyRole(Role.ADMINISTRATOR.name(), Role.ASSISTANT_ADMINISTRATOR.name());

        authReqConfig.requestMatchers(HttpMethod.POST, "/categories")
                .hasRole(Role.ADMINISTRATOR.name());

        authReqConfig.requestMatchers(HttpMethod.PUT, "/categories/{categoryId")
                .hasAnyRole(Role.ADMINISTRATOR.name(), Role.ASSISTANT_ADMINISTRATOR.name());

        authReqConfig.requestMatchers(HttpMethod.PUT, "/categories/{categoryId}/disabled")
                .hasRole(Role.ADMINISTRATOR.name());

        authReqConfig.requestMatchers(HttpMethod.PUT, "/auth/profile")
                .hasAnyRole(Role.ADMINISTRATOR.name(), Role.ASSISTANT_ADMINISTRATOR.name(), Role.CUSTOMER.name());
*/
                    /*
                    Authorization public endpoints
                     */
        authReqConfig.requestMatchers(HttpMethod.POST,"/customer").permitAll();
        authReqConfig.requestMatchers(HttpMethod.POST,"/auth/authenticate").permitAll();
        authReqConfig.requestMatchers(HttpMethod.GET,"/auth/validate-token").permitAll();

        authReqConfig.anyRequest().authenticated();
    }
}
