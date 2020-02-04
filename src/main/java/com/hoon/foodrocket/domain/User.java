package com.hoon.foodrocket.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Builder    // builder 패턴을 사용
@Getter     // getter 메소드 생성
@NoArgsConstructor  // 파라미터가 없는 빈 생성자 생성
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자 생성
public class User {

    private Long id;

    @NotEmpty   // 해당 요소는 null 이거나 비어서는 안 됨
    private String email;

    @NotEmpty
    private String name;

    @NotEmpty
    private String password;

    public boolean isMatchPassword(String password) {
        if (password == null) {
            return false;
        }

        return this.password.equals(password);
    }
}
