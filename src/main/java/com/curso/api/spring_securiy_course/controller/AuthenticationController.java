package com.curso.api.spring_securiy_course.controller;

import com.curso.api.spring_securiy_course.dto.auth.AuthenticationRequest;
import com.curso.api.spring_securiy_course.dto.auth.AuthenticationResponse;
import com.curso.api.spring_securiy_course.persistence.entity.User;
import com.curso.api.spring_securiy_course.service.auth.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/validate-token")
    public ResponseEntity<Boolean> validate(@RequestParam String jwt){
        boolean isTokenValid = authenticationService.validateToken(jwt);
        return ResponseEntity.ok(isTokenValid);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody @Valid AuthenticationRequest authenticationrequest){
        AuthenticationResponse rsp = authenticationService.login(authenticationrequest);
        return ResponseEntity.ok(rsp);
    }
    @GetMapping("/profile")
    public ResponseEntity<User> findMyProfile(){
        User user = authenticationService.findLoggedInUser();
        return ResponseEntity.ok(user);
    }
}
