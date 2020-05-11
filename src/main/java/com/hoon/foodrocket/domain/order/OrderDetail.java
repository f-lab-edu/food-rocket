package com.hoon.foodrocket.domain.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.hoon.foodrocket.service.payment.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
    private Long id;

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

    @NotEmpty
    private OrderStatus orderStatus;    // 주문 상태

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date regDate;  // 주문일

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private List<OrderMenu> orderMenuList;   // 메뉴 목록
}
