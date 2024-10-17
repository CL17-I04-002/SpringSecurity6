package com.curso.api.spring_securiy_course.service;

import com.curso.api.spring_securiy_course.dto.SaveCategory;
import com.curso.api.spring_securiy_course.persistence.entity.Category;
import com.curso.api.spring_securiy_course.persistence.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CategoryService {
    Page<Category> findAll(Pageable pageable);

    Optional<Category> findOneById(Long categoryId);

    Category createOne(SaveCategory saveCategory);

    Category updateOneById(Long categoryId, SaveCategory saveCategory);

    Category disabledOneById(Long categoryId);
}
