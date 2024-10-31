package com.curso.api.spring_securiy_course.persistence.repository;

import com.curso.api.spring_securiy_course.persistence.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String defaultRole);
}
