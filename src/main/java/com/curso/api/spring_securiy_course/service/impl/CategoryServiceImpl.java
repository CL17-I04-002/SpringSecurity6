package com.curso.api.spring_securiy_course.service.impl;

import com.curso.api.spring_securiy_course.dto.SaveCategory;
import com.curso.api.spring_securiy_course.dto.SaveProduct;
import com.curso.api.spring_securiy_course.exception.ObjectNotFoundException;
import com.curso.api.spring_securiy_course.persistence.entity.Category;
import com.curso.api.spring_securiy_course.persistence.entity.Product;
import com.curso.api.spring_securiy_course.persistence.repository.CategoryRepository;
import com.curso.api.spring_securiy_course.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Optional<Category> findOneById(Long cateogryId) {
        return categoryRepository.findById(cateogryId);
    }

    @Override
    public Category createOne(SaveCategory saveCategory) {
        Category categoryFromDB = new Category();
        categoryFromDB.setName(saveCategory.getName());
        categoryFromDB.setStatus(Category.CategoryStatus.ENABLED);

        return categoryRepository.save(categoryFromDB);
    }

    @Override
    public Category updateOneById(Long categoryId, SaveCategory saveCategory) {
        Category categoryFromDB = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ObjectNotFoundException("Category not found with id: " + categoryId));
        categoryFromDB.setName(saveCategory.getName());
        categoryFromDB.setStatus(Category.CategoryStatus.ENABLED);

        return categoryRepository.save(categoryFromDB);
    }

    @Override
    public Category disabledOneById(Long categoryId) {
        Category categoryFromDB = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ObjectNotFoundException("Category not found with id: " + categoryId));
        categoryFromDB.setStatus(Category.CategoryStatus.DISABLED);

        return categoryRepository.save(categoryFromDB);
    }
}

