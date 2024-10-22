package com.curso.api.spring_securiy_course.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * User saved in actual thread
 */
@Getter
@Setter
public class SaveUser implements Serializable {
    @Size(min = 4)
    private String name;
    @Size(min = 4)
    private String username;
    @Size(min = 8)
    private String password;
    @Size(min = 8)
    private String repeatedPassword;
}
