package com.curso.api.spring_securiy_course.service;

import com.curso.api.spring_securiy_course.dto.SavePermission;
import com.curso.api.spring_securiy_course.dto.ShowPermission;
import com.curso.api.spring_securiy_course.persistence.entity.GrantedPermission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PermissionService {
    Page<ShowPermission> findAll(Pageable pageable);

    Optional<ShowPermission> findOneById(Long permissionId);

    ShowPermission createOne(SavePermission savePermission);

    ShowPermission delereOneById(Long permissionId);
}
