package com.curso.api.spring_securiy_course.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ShowPermission implements Serializable {
    private Long id;
    private String module;
    private String role;
    private String httpMethod;
}
