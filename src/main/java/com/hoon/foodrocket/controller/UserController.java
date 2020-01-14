package com.hoon.foodrocket.controller;

import com.hoon.foodrocket.application.UserService;
import com.hoon.foodrocket.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/{id}")
    public User detail(@PathVariable("id") Long id) {
        return userService.getUser(id);
    }

    @PostMapping("/users")
    public ResponseEntity<?> create(@RequestBody User resource) {
        String email = resource.getEmail();
        String name = resource.getName();
        String password = resource.getPassword();

        userService.registerUser(email, name, password);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
