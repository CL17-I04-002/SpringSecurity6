package com.curso.api.spring_securiy_course.service;

import com.curso.api.spring_securiy_course.dto.SaveUser;
import com.curso.api.spring_securiy_course.persistence.entity.User;
import jakarta.validation.Valid;

/**
 * Interface that configures register
 */
public interface UserService {
    User registerOneCustomer(@Valid SaveUser newUser);
}
