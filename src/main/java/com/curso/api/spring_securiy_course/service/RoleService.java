package com.curso.api.spring_securiy_course.service;

import com.curso.api.spring_securiy_course.persistence.entity.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findDefaultRole();
}
