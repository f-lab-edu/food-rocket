package com.hoon.foodrocket.config;

import com.hoon.foodrocket.application.payment.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
class PaymentConfig {
    @Bean
    public Map<PaymentMethod, PaymentProcessInterface> paymentMap() {
        Map<PaymentMethod, PaymentProcessInterface> map = new HashMap<>();

        map.put(PaymentMethod.CARD, new CardPaymentProcess());
        map.put(PaymentMethod.PHONE, new PhonePaymentProcess());
        map.put(PaymentMethod.KAKAO, new KakaoPaymentProcess());

        return map;
    }
}
