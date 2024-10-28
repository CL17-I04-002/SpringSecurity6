package com.curso.api.spring_securiy_course.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/users")
public class UserController {
    /**
     * We compare principal's user and path's user
     * @param username
     * @return
     * @throws AccessDeniedException
     */
    @GetMapping("/{username}")
    public ResponseEntity<String> compareUser(@PathVariable String username) throws AccessDeniedException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userPrincipal = (String) auth.getPrincipal();
        if(username.equals(userPrincipal)) return ResponseEntity.ok("Es el mismo usuario");
        else throw new AccessDeniedException("Usernames don't match");
    }
}
