package com.hoon.foodrocket.domain;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    @Setter
    private Long id;

    @NotEmpty
    private String name;    // 가게 이름

    @NotEmpty
    private String address;    // 가게 주소

    @Setter
    private String ownerEmail;  // 사장 이메일

    @NotEmpty
    private String information; // 가게 정보

    @NotEmpty
    private String phoneNumber; // 가게 번호

    @NotEmpty
    private String openTime;    // 오픈 시간

    @NotEmpty
    private String closeTime;    // 마감 시간

    @NotEmpty
    private String category;    // 카테고리

    public boolean isNotMatchOwnerEmail(String loginUserEmail) {
        return !this.ownerEmail.equals(loginUserEmail);
    }
}
