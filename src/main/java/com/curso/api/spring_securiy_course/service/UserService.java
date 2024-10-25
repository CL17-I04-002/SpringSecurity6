package com.curso.api.spring_securiy_course.service;

import com.curso.api.spring_securiy_course.dto.SaveUser;
import com.curso.api.spring_securiy_course.persistence.entity.User;
import jakarta.validation.Valid;

import java.util.Optional;

/**
 * Interface that configures register
 */
public interface UserService {
    User registerOneCustomer(@Valid SaveUser newUser);
    Optional<User> findOneByUsername(String username);
}
