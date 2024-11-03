package com.curso.api.spring_securiy_course.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class LogoutResponse implements Serializable {
    private String message;
}
