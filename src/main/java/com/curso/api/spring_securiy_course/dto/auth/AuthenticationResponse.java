package com.curso.api.spring_securiy_course.dto.auth;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AuthenticationResponse implements Serializable {
    private String jwt;
}
