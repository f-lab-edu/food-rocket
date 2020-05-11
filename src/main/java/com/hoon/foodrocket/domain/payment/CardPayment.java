package com.hoon.foodrocket.domain.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CardPayment extends Payment {
    private String cardInfo;
}
