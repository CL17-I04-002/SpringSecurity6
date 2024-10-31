package com.curso.api.spring_securiy_course.service.impl;

import com.curso.api.spring_securiy_course.persistence.entity.Role;
import com.curso.api.spring_securiy_course.persistence.repository.RoleRepository;
import com.curso.api.spring_securiy_course.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Value("${security.default.role}")
    private String defaultRole;

    @Override
    public Optional<Role> findDefaultRole() {
        return roleRepository.findByName(defaultRole);
    }
}
