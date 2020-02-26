package com.hoon.foodrocket.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Menu {

    private Long id;

    @NotEmpty
    private String name;    // 메뉴 이름

    @NotEmpty
    private String price;   // 메뉴 가격

    @NotNull
    private Long restaurantId;  // 메뉴가 등록될 레스토랑 번호

    @Setter
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean destroy;    // 삭제 여부

    public boolean isDestroy() {
        return destroy;
    }

}
