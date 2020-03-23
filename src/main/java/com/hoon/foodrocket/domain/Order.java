package com.hoon.foodrocket.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long id;

    @Setter
    private String userEmail;   // 유저 이메일

    @NotEmpty
    private String phoneNumber;   // 주문자 번호

    @NotEmpty
    private String deliveryAddress;    // 배달 주소

    @NotEmpty
    private String restaurantName;  // 가게 이름

    @NotEmpty
    private String request;  // 요청사항

    @NotNull
    private int paymentAmount;  // 결제금액

    @NotEmpty
    private String paymentMethod;   // 결제방식

    @NotEmpty
    private String status;  // 주문 상태

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private List<CartItem> cartItemList;   // 메뉴 목록
}
