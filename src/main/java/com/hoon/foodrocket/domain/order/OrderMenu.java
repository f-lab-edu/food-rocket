package com.hoon.foodrocket.domain.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderMenu {
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String price;

    @NotNull
    private int amount;
}