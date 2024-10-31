package com.curso.api.spring_securiy_course.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String path;

    private String httpMethod;

    private boolean permitAll;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;
}
