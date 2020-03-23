package com.hoon.foodrocket.application;

import com.hoon.foodrocket.application.payment.PaymentService;
import com.hoon.foodrocket.domain.Order;
import com.hoon.foodrocket.domain.OrderHistory;
import com.hoon.foodrocket.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {
    private CartService cartService;
    private PaymentService paymentService;
    private OrderMapper orderMapper;

    @Autowired
    public OrderService(CartService cartService, PaymentService paymentService, OrderMapper orderMapper) {
        this.cartService = cartService;
        this.paymentService = paymentService;
        this.orderMapper = orderMapper;
    }

    public List<OrderHistory> getOrders(String loginUserEmail) {
        return orderMapper.getOrderHistoryList(loginUserEmail);   // 주문 목록
    }

    /**
     * 주문은 총 3단계로 이루어진다.
     * 1. 결제 진행
     * 주문을 위해 전달받은 오더 파라미터를 paymentService.process()에 넘긴다.
     * process()는 오더에 정의된 결제방식에 따라 결제를 진행한다.
     * 결제 완료 후 결과값을 반환한다.
     * <p>
     * 2. 디비에 주문 등록
     * 오더 테이블에 주문 정보를 등록한다.
     * 오더 메뉴 테이블에 오더의 메뉴 정보를 등록한다.
     * <p>
     * 3. 장바구니 비우기
     * 결제와 디비 등록까지 완료된 이후 장바구니를 비운다.
     *
     * @param order
     * @param loginUserEmail
     */
    @Transactional
    public void registerOrder(Order order, String loginUserEmail) {
        // 결제 진행
        boolean result = paymentService.process(order);

        if (result) {
            // 디비에 주문 등록
            orderMapper.insertOrder(order);
            orderMapper.insertOrderMenus(order.getCartItemList(), order.getId());

            // 장바구니 비우기
            cartService.clearItem(loginUserEmail);
        }

    }
}
