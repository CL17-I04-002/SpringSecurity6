package com.curso.api.spring_securiy_course.service.auth;

import com.curso.api.spring_securiy_course.dto.RegisteredUser;
import com.curso.api.spring_securiy_course.dto.SaveUser;
import com.curso.api.spring_securiy_course.persistence.entity.User;
import com.curso.api.spring_securiy_course.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

    public RegisteredUser registerOneCustomer(@Valid SaveUser newUser) {
        User user = userService.registerOneCustomer(newUser);

        RegisteredUser userDto = new RegisteredUser();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setUsername(user.getUsername());
        userDto.setRole(user.getRole().name());

        String jwt = jwtService.generateToken(user, generateExtraClaims(user));
        userDto.setJwt(jwt);

        return userDto;
    }
    private Map<String, Object> generateExtraClaims(User user){
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", user.getName());
        extraClaims.put("role", user.getRole().name());
        extraClaims.put("authorities", user.getAuthorities());

        return extraClaims;
    }
}
