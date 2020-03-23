package com.hoon.foodrocket.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderHistory {
    private Long id;    // 주문 id

    @NotEmpty
    private String restaurantName;  // 가게 이름

    @NotNull
    private int paymentAmount;  // 결제금액

    @NotNull
    private Timestamp regDate;  // 주문일

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private List<OrderMenu> orderMenuList;   // 메뉴 목록
}
