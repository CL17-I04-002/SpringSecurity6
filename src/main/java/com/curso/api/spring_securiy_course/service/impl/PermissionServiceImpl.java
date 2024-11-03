package com.curso.api.spring_securiy_course.service.impl;

import com.curso.api.spring_securiy_course.dto.SavePermission;
import com.curso.api.spring_securiy_course.dto.ShowPermission;
import com.curso.api.spring_securiy_course.exception.ObjectNotFoundException;
import com.curso.api.spring_securiy_course.persistence.entity.GrantedPermission;
import com.curso.api.spring_securiy_course.persistence.entity.Operation;
import com.curso.api.spring_securiy_course.persistence.entity.Role;
import com.curso.api.spring_securiy_course.persistence.repository.OperationRepository;
import com.curso.api.spring_securiy_course.persistence.repository.PermissionRepository;
import com.curso.api.spring_securiy_course.persistence.repository.RoleRepository;
import com.curso.api.spring_securiy_course.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private OperationRepository operationRepository;

    @Override
    public Page<ShowPermission> findAll(Pageable pageable) {
        return permissionRepository.findAll(pageable).map(this::mapEntityToShowDto);
    }

    @Override
    public Optional<ShowPermission> findOneById(Long permissionId) {
        return permissionRepository.findById(permissionId).map(this::mapEntityToShowDto);
    }

    @Override
    public ShowPermission createOne(SavePermission savePermission) {
        GrantedPermission grantedPermission = new GrantedPermission();

        Role role = roleRepository.findByName(savePermission.getRole())
                .orElseThrow(() -> new ObjectNotFoundException("Role has not found"));
        Operation operation = operationRepository.findByName(savePermission.getOperation())
                        .orElseThrow(() -> new ObjectNotFoundException("Operation has not found"));
        grantedPermission.setRole(role);
        grantedPermission.setOperation(operation);
        permissionRepository.save(grantedPermission);
        return this.mapEntityToShowDto(grantedPermission);
    }

    @Override
    public ShowPermission delereOneById(Long permissionId) {
        GrantedPermission grantedPermission = permissionRepository.findById(permissionId)
                        .orElseThrow(() -> new ObjectNotFoundException("Permission has not found"));
        permissionRepository.delete(grantedPermission);
        return this.mapEntityToShowDto(grantedPermission);
    }

    private ShowPermission mapEntityToShowDto(GrantedPermission grantedPermission) {
        ShowPermission showDto = new ShowPermission();
        showDto.setId(grantedPermission.getId());
        showDto.setRole(grantedPermission.getRole().getName());
        showDto.setModule(grantedPermission.getOperation().getModule().getName());
        showDto.setHttpMethod(grantedPermission.getOperation().getHttpMethod());

        return showDto;
    }
}
