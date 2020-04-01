package com.hoon.foodrocket.service.payment;

import com.hoon.foodrocket.domain.payment.Payment;

public class PhonePaymentProcess implements PaymentProcessInterface {
    @Override
    public boolean paymentProcess(Payment payment) {
        return true;
    }
}
