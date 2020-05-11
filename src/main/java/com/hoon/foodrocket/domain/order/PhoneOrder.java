package com.hoon.foodrocket.domain.order;

import com.hoon.foodrocket.domain.payment.PhonePayment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PhoneOrder extends Order {
    private PhonePayment phonePayment;
}
