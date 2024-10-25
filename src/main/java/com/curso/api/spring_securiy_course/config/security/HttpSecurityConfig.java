package com.curso.api.spring_securiy_course.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class HttpSecurityConfig {
    @Autowired
    private AuthenticationProvider daoAuthProvider;

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
                .authorizeHttpRequests( authReqConfig -> {
                   authReqConfig.requestMatchers(HttpMethod.POST,"/customer").permitAll();
                   authReqConfig.requestMatchers(HttpMethod.POST,"/auth/authenticate").permitAll();
                    authReqConfig.requestMatchers(HttpMethod.GET,"/auth/validate-token").permitAll();

                   authReqConfig.anyRequest().authenticated();
                })
                .build();
    }
}
