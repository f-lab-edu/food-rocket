package com.hoon.foodrocket.domain.order;

import com.hoon.foodrocket.domain.payment.KakaoPayment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class KakaoOrder extends Order {
    private KakaoPayment kakaoPayment;
}
