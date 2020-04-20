package com.hoon.foodrocket.service.payment;

import com.hoon.foodrocket.domain.payment.Payment;
import org.springframework.stereotype.Service;

/**
 * 기존에 OrderController 에서 메소드를 호출할때마다 결제 프로세스 객체를 새로 생성해서 넘겨줬는데
 * 결제 프로세스는 별도의 상태를 저장하지 않으므로 객체를 새로 생성하는 것이 서버 입장에서는 부담일 수 있습니다.
 * <p>
 * 이는 new 로 객체를 생성하면 heap 영역에 메모리를 할당하게 되는데 다수의 사용자가 몰리게 되는 상황에서
 * OutOfMemoryException 을 일으킬 수 있기 때문입니다.
 * <p>
 * 이를 방지하고자 결제 프로세스를 스프링 빈에 등록시켜 싱글톤으로 관리함으로써 메모리 낭비를 방지하고
 * 해당 클래스가 필요한 곳에 의존성을 주입해서 사용할 수 있도록 했습니다.
 */
@Service
public class CardPaymentProcess implements PaymentProcessInterface {
    @Override
    public boolean paymentProcess(Payment payment) {
        return true;
    }
}
