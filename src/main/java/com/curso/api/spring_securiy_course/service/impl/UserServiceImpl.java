package com.curso.api.spring_securiy_course.service.impl;

import com.curso.api.spring_securiy_course.dto.SaveUser;
import com.curso.api.spring_securiy_course.exception.InvalidPasswordException;
import com.curso.api.spring_securiy_course.persistence.entity.User;
import com.curso.api.spring_securiy_course.persistence.repository.UserRepository;
import com.curso.api.spring_securiy_course.persistence.util.Role;
import com.curso.api.spring_securiy_course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

/**
 * Implement UserService
 */
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerOneCustomer(SaveUser newUser) {

        validatePassword(newUser);

        User user = new User();
        user.setName(newUser.getName());
        user.setUsername(newUser.getUsername());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setRole(Role.ROLE_CUSTOMER);
        return userRepository.save(user);
    }
    private void validatePassword(SaveUser dto){
        if(!StringUtils.hasText(dto.getPassword()) || !StringUtils.hasText(dto.getRepeatedPassword())){
            throw new InvalidPasswordException("Password don't match");
        }
        if(!dto.getPassword().equals(dto.getRepeatedPassword())){
            throw new InvalidPasswordException("Password don't match");
        }
    }
}