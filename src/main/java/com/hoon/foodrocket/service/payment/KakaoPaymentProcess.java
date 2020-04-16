package com.hoon.foodrocket.service.payment;

import com.hoon.foodrocket.domain.payment.Payment;
import org.springframework.stereotype.Service;

@Service
public class KakaoPaymentProcess implements PaymentProcessInterface {
    @Override
    public boolean paymentProcess(Payment payment) {
        return true;
    }
}
