package com.curso.api.spring_securiy_course.controller;

import com.curso.api.spring_securiy_course.dto.SavePermission;
import com.curso.api.spring_securiy_course.dto.ShowPermission;
import com.curso.api.spring_securiy_course.service.PermissionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/permissions")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @GetMapping
    public ResponseEntity<Page<ShowPermission>> findAll(Pageable pageable){
        Page permissionPage = permissionService.findAll(pageable);
        if(permissionPage.hasContent()){
            return ResponseEntity.ok(permissionPage);
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/{permissionId}")
    public ResponseEntity<ShowPermission> findOneById(@PathVariable Long permissionId){
         Optional<ShowPermission> showPermission = permissionService.findOneById(permissionId);
         if(showPermission.isPresent()){
             return ResponseEntity.ok(showPermission.get());
         }
         return ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<ShowPermission> createOnePermission(@RequestBody @Valid SavePermission savePermission){
        ShowPermission showPermission = permissionService.createOne(savePermission);

        return ResponseEntity.ok(showPermission);
    }
    @DeleteMapping("/{permissionId}")
    public ResponseEntity<ShowPermission> deleteOnePermission(@PathVariable Long permissionId){
        ShowPermission showPermission = permissionService.delereOneById(permissionId);

        return ResponseEntity.ok(showPermission);
    }
}
