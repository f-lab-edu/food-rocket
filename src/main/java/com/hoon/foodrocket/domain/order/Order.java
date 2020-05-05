package com.hoon.foodrocket.domain.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hoon.foodrocket.domain.CartItem;
import com.hoon.foodrocket.service.payment.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private PaymentMethod paymentMethod;   // 결제방식

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private List<CartItem> cartItemList;   // 메뉴 목록
}
