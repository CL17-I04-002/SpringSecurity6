package com.curso.api.spring_securiy_course.service;

import com.curso.api.spring_securiy_course.dto.SaveProduct;
import com.curso.api.spring_securiy_course.persistence.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductService {
    Page<Product> findAll(Pageable pageable);

    Optional<Product> findOneById(Long productId);

    Product createOne(SaveProduct saveProduct);

    Product updateOneById(Long productId, SaveProduct saveProduct);

    Product disabledOneById(Long productId);
}
