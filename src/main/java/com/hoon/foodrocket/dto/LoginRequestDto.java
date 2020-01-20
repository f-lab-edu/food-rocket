package com.hoon.foodrocket.dto;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class LoginRequestDto {
    @NotEmpty
    private String email;

    @NotEmpty
    private String password;
}