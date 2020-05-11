package com.hoon.foodrocket.domain.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@AllArgsConstructor
public class OrderHistory {
    private Long id;    // 주문 id

    @NotEmpty
    private String restaurantName;  // 가게 이름

    @NotNull
    private int paymentAmount;  // 결제금액

    @NotEmpty
    private OrderStatus orderStatus;  // 주문 상태

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date regDate;  // 주문일

    @NotEmpty
    private String menuName;    // 주문한 메뉴 중 첫번째

    @NotNull
    private int extraMenuNum;   // 주문한 메뉴 갯수에서 하나를 뺀 값
}
