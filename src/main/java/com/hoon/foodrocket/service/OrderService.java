package com.hoon.foodrocket.service;

import com.hoon.foodrocket.domain.order.Order;
import com.hoon.foodrocket.domain.order.OrderDetail;
import com.hoon.foodrocket.domain.order.OrderHistory;
import com.hoon.foodrocket.domain.payment.Payment;
import com.hoon.foodrocket.mapper.OrderMapper;
import com.hoon.foodrocket.service.payment.PaymentProcessInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {
    private CartService cartService;
    private OrderMapper orderMapper;

    @Autowired
    public OrderService(CartService cartService, OrderMapper orderMapper) {
        this.cartService = cartService;
        this.orderMapper = orderMapper;
    }

    public List<OrderHistory> getOrders(Long cursorId, String loginUserEmail) {
        return orderMapper.getOrderHistoryList(cursorId, loginUserEmail);   // 주문 내역 목록
    }

    public OrderDetail getOrderDetail(Long id, String loginUserEmail) {
        return orderMapper.getOrderDetail(id, loginUserEmail);
    }

    /**
     * 주문은 총 3단계로 이루어진다.
     * 1. 디비에 주문 등록
     * 오더 테이블에 주문 정보를 등록한다.
     * 오더 메뉴 테이블에 오더의 메뉴 정보를 등록한다.
     * <p>
     * 2. 결제 진행
     * 주문을 위해 전달받은 오더 파라미터를 paymentProcess()에 넘긴다.
     * process()는 오더에 정의된 결제방식에 따라 결제를 진행한다.
     * 결제 완료 후 결과값을 반환한다.
     * <p>
     * 3. 장바구니 비우기
     * 결제와 디비 등록까지 완료된 이후 장바구니를 비운다.
     *
     * @param order
     * @param loginUserEmail
     */
    @Transactional
    public void registerOrder(Order order, Payment payment, String loginUserEmail, PaymentProcessInterface paymentProcessInterface) {
        // 디비에 주문 등록
        orderMapper.insertOrder(order);
        orderMapper.insertOrderMenus(order.getCartItemList(), order.getId());

        // 결제 진행
        boolean result = paymentProcessInterface.paymentProcess(payment);

        if (result) {
            // 장바구니 비우기
            cartService.clearItem(loginUserEmail);
        }

    }

    public void updateOrderStatus(String status, Long id) {
        orderMapper.updateOrderStatus(status, id);
    }
}
