package com.hoon.foodrocket.domain.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PhonePayment extends Payment {
    private String phoneInfo;
}
