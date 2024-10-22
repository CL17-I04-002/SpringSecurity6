package com.curso.api.spring_securiy_course.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Return user authenticated even jwt
 */
@Getter
@Setter
public class RegisteredUser implements Serializable {
    private Long id;
    private String username;
    private String name;
    private String role;
    private String jwt;
}
