package com.curso.api.spring_securiy_course.service.impl;

import com.curso.api.spring_securiy_course.dto.SaveUser;
import com.curso.api.spring_securiy_course.exception.InvalidPasswordException;
import com.curso.api.spring_securiy_course.exception.ObjectNotFoundException;
import com.curso.api.spring_securiy_course.persistence.entity.Role;
import com.curso.api.spring_securiy_course.persistence.entity.User;
import com.curso.api.spring_securiy_course.persistence.repository.UserRepository;
import com.curso.api.spring_securiy_course.service.RoleService;
import com.curso.api.spring_securiy_course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * Implement UserService
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @Override
    public User registerOneCustomer(SaveUser newUser) {

        validatePassword(newUser);

        User user = new User();
        user.setName(newUser.getName());
        user.setUsername(newUser.getUsername());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        Role defaultRole = roleService.findDefaultRole()
                        .orElseThrow(() -> new ObjectNotFoundException("Role not found. Default Role"));
        user.setRole(defaultRole);
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findOneByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    /**
     * If doesn't match the passwords
     * @param dto
     */
    private void validatePassword(SaveUser dto){
        if(!StringUtils.hasText(dto.getPassword()) || !StringUtils.hasText(dto.getRepeatedPassword())){
            throw new InvalidPasswordException("Password don't match");
        }
        if(!dto.getPassword().equals(dto.getRepeatedPassword())){
            throw new InvalidPasswordException("Password don't match");
        }
    }
}
