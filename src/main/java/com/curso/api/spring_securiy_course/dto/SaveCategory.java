package com.curso.api.spring_securiy_course.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
public class SaveCategory implements Serializable {
    @NotBlank
    private String name;
}
