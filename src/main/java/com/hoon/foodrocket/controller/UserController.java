package com.hoon.foodrocket.controller;

import com.hoon.foodrocket.service.UserService;
import com.hoon.foodrocket.domain.User;
import com.hoon.foodrocket.dto.LoginRequestDto;
import com.hoon.foodrocket.util.HttpSessionUtil;
import com.hoon.foodrocket.util.SHA256Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User detail(@PathVariable("id") Long id) {
        User user = userService.getUserFromId(id);

        if (user == null) {
            throw new IllegalStateException("정보(유저)가 없습니다.");
        }

        return user;
    }

    @PostMapping
    public HttpStatus create(@RequestBody User resource) {
        User builder = generateUser(resource);
        userService.registerUser(builder);

        return HttpStatus.CREATED;
    }

    private User generateUser(User resource) {
        return User.builder()
                .email(resource.getEmail())
                .name(resource.getName())
                .password(SHA256Util.encode(resource.getPassword()))
                .address(resource.getAddress())
                .region(resource.getRegion())
                .build();
    }

    @PostMapping("/login")
    public HttpStatus login(@RequestBody LoginRequestDto resource, HttpSession session) {
        String email = resource.getEmail();
        String password = resource.getPassword();

        User user = userService.login(email, password);

        HttpSessionUtil.setLoginUserEmail(session, user.getEmail());

        return HttpStatus.OK;
    }

    @GetMapping("/logout")
    public void logout(HttpSession session) {
        HttpSessionUtil.logout(session);
    }
}
