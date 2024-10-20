package com.curso.api.spring_securiy_course.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ApiError implements Serializable {
    private String backendMessage;
    private String message;
    private LocalDateTime timestamp;
    private String url;
    private String method;
}
