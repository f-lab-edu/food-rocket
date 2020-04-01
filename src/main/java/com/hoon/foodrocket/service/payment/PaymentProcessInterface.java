package com.hoon.foodrocket.service.payment;

import com.hoon.foodrocket.domain.payment.Payment;

public interface PaymentProcessInterface {
    boolean paymentProcess(Payment payment);
}