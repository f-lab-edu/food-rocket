package com.hoon.foodrocket.application.payment;

import com.hoon.foodrocket.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PaymentService {
    Map<PaymentMethod, PaymentProcessInterface> map;

    @Autowired
    public PaymentService(Map<PaymentMethod, PaymentProcessInterface> map) {
        this.map = map;
    }

    public boolean process(Order order) {
        // 결제 모듈이 추가될 경우 PaymentConfig.java 에 추가해야함
        PaymentMethod method = PaymentMethod.valueOf(order.getPaymentMethod());

        return map.get(method).paymentProcess();
    }
}
