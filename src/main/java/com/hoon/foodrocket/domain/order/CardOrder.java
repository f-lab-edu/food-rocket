package com.hoon.foodrocket.domain.order;

import com.hoon.foodrocket.domain.payment.CardPayment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CardOrder extends Order {
    private CardPayment cardPayment;
}
