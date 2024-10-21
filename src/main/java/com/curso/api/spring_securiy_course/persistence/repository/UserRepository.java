package com.curso.api.spring_securiy_course.persistence.repository;

import com.curso.api.spring_securiy_course.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
