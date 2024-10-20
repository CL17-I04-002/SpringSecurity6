package com.curso.api.spring_securiy_course.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class SaveProduct implements Serializable {
    @NotBlank
    private String name;
    @DecimalMin(value = "0.01", message = "el precio debe de ser mayor a cero")
    private BigDecimal price;
    @Min(value = 1)
    private Long categoryId;
}
