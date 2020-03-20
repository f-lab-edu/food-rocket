package com.hoon.foodrocket.controller;

import com.hoon.foodrocket.application.OwnerService;
import com.hoon.foodrocket.domain.Owner;
import com.hoon.foodrocket.dto.LoginRequestDto;
import com.hoon.foodrocket.util.HttpSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    @Autowired
    private OwnerService ownerService;

    @GetMapping("/{id}")
    public Owner detail(@PathVariable("id") Long id) {
        Owner owner = ownerService.getOwnerFromId(id);

        if (owner == null) {
            throw new IllegalStateException("정보(사장)가 없습니다.");
        }

        return owner;
    }

    @PostMapping
    public HttpStatus create(@RequestBody Owner resource) {
        String email = resource.getEmail();
        String name = resource.getName();
        String password = resource.getPassword();

        ownerService.registerOwner(email, name, password);

        return HttpStatus.CREATED;
    }

    @PostMapping("/login")
    public HttpStatus login(@RequestBody LoginRequestDto resource, HttpSession session) {
        String email = resource.getEmail();
        String password = resource.getPassword();

        Owner owner = ownerService.login(email, password);

        HttpSessionUtil.setLoginOwnerEmail(session, owner.getEmail());

        return HttpStatus.OK;
    }

    @GetMapping("/logout")
    public void logout(HttpSession session) {
        HttpSessionUtil.logout(session);
    }
}
