package com.hoon.foodrocket.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CartItem {
    String name;

    String price;

    int amount;
}