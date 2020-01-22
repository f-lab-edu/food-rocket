package com.hoon.foodrocket.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String address;

    @NotEmpty
    private String ownerEmail;

    public boolean matchOwnerEmail(String loginUserEmail) {
        if (loginUserEmail == null) {
            return false;
        }

        return this.ownerEmail.equals(loginUserEmail);
    }
}
