package com.curso.api.spring_securiy_course.persistence.repository;

import com.curso.api.spring_securiy_course.persistence.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
