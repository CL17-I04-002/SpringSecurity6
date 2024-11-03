package com.curso.api.spring_securiy_course.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SavePermission implements Serializable {
    @NotBlank
    private String role;

    @NotBlank
    private String operation;
}
