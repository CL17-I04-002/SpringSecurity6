package com.curso.api.spring_securiy_course.controller;

import com.curso.api.spring_securiy_course.dto.SaveCategory;
import com.curso.api.spring_securiy_course.persistence.entity.Category;
import com.curso.api.spring_securiy_course.persistence.entity.Product;
import com.curso.api.spring_securiy_course.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'ASSISTANT_ADMINISTRATOR')")
    @GetMapping
    public ResponseEntity<Page<Category>> findAll(Pageable pageable){
        Page<Category> categoriesPage = categoryService.findAll(pageable);
        if(categoriesPage.hasContent()){
            return ResponseEntity.ok(categoriesPage);
        }
        return ResponseEntity.notFound().build();
    }
    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'ASSISTANT_ADMINISTRATOR')")
    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> findOneById(@PathVariable Long categoryId){
        Optional<Category> category = categoryService.findOneById(categoryId);
        if(category.isPresent()){
            return ResponseEntity.ok(category.get());
        }
        return ResponseEntity.notFound().build();
    }
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PostMapping
    public ResponseEntity<Category> createOne(@RequestBody @Valid SaveCategory saveCategory){
        Category category = categoryService.createOne(saveCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }
    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'ASSISTANT_ADMINISTRATOR')")
    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> updateOneById(@RequestBody @Valid SaveCategory saveCategory,
                                                 @PathVariable Long categoryId){
        Category category = categoryService.updateOneById(categoryId, saveCategory);
        return ResponseEntity.ok(category);
    }
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PutMapping("/{categoryId}/disabled")
    public ResponseEntity<Category> disabledOneById(@PathVariable Long categoryId){
        Category category = categoryService.disabledOneById(categoryId);
        return ResponseEntity.ok(category);
    }
}

