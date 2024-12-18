package com.curso.api.spring_securiy_course.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private ProductStatus status;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    public static enum ProductStatus {
        ENABLED, DISABLED;
    }
}
