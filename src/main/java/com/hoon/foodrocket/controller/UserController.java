package com.hoon.foodrocket.controller;

import com.hoon.foodrocket.application.UserService;
import com.hoon.foodrocket.domain.User;
import com.hoon.foodrocket.dto.LoginRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto resource, HttpSession session) {
        String email = resource.getEmail();
        String password = resource.getPassword();

        User user = userService.login(email, password);

        session.setAttribute("user", user.getEmail());

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/logout")
    public void logout(HttpSession session) {
        session.invalidate();
    }
}
