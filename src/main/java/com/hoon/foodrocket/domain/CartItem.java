package com.hoon.foodrocket.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class CartItem {
    @NotEmpty
    private String name;

    @NotEmpty
    private String price;

    @NotNull
    private int amount;
}