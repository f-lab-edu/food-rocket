package com.hoon.foodrocket.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
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