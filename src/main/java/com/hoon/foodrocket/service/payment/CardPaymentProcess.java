package com.hoon.foodrocket.service.payment;

import com.hoon.foodrocket.domain.payment.Payment;
import org.springframework.stereotype.Service;

/**
 * 기존에 OrderController 에서 메소드를 호출할때마다 결제 프로세스 객체를 새로 생성해서 넘겨줬는데
 * 결제 프로세스는 별도의 상태를 저장하지 않으므로 객체를 새로 생성하는 것이 서버 입장에서 부담이기 때문에
 * 이를 방지하고자 결제 프로세스를 스프링 빈에 등록시킴으로써 싱글톤으로 관리하도록 함
 */
@Service
public class CardPaymentProcess implements PaymentProcessInterface {
    @Override
    public boolean paymentProcess(Payment payment) {
        return true;
    }
}
