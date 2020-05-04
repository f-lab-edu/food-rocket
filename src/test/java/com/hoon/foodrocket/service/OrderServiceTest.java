package com.hoon.foodrocket.service;

import com.hoon.foodrocket.domain.CartItem;
import com.hoon.foodrocket.domain.order.Order;
import com.hoon.foodrocket.domain.payment.Payment;
import com.hoon.foodrocket.mapper.OrderMapper;
import com.hoon.foodrocket.service.payment.PaymentMethod;
import com.hoon.foodrocket.service.payment.PaymentProcessInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private CartService cartService;

    @Mock
    private PaymentProcessInterface paymentProcessInterface;

    @Test
    public void 주문_성공() throws Exception {
        // given
        Order order = generateOrder();
        Payment payment = new Payment("결제");
        String loginUserEmail = "test@example.com";

        given(paymentProcessInterface.paymentProcess(payment)).willReturn(true);

        // when
        orderService.registerOrder(order, payment, loginUserEmail, paymentProcessInterface);

        // then
        verify(orderMapper).insertOrder(any());
        verify(orderMapper).insertOrderMenus(any(), any());
        verify(orderMapper).updateOrderStatus(any(), any());
        verify(cartService).clearItem(loginUserEmail);
    }

    @Test
    public void 결제_실패() throws Exception {
        // given
        Order order = generateOrder();
        Payment payment = new Payment("결제");
        String loginUserEmail = "test@example.com";

        given(paymentProcessInterface.paymentProcess(payment)).willReturn(false);

        // when
        orderService.registerOrder(order, payment, loginUserEmail, paymentProcessInterface);

        // then
        verify(orderMapper).insertOrder(any());
        verify(orderMapper).insertOrderMenus(any(), any());
        verify(orderMapper, never()).updateOrderStatus(any(), any());
        verify(cartService, never()).clearItem(loginUserEmail);
    }

    private Order generateOrder() {
        CartItem cartItem = new CartItem("테스트", "1000", 10);
        CartItem cartItem2 = new CartItem("테스트2", "2000", 10);

        List<CartItem> cartItemList = new ArrayList<>();
        cartItemList.add(cartItem);
        cartItemList.add(cartItem2);

        return Order.builder()
                .phoneNumber("010-1111-2222")
                .deliveryAddress("금오동")
                .restaurantName("테스트")
                .request("테스트입니다.")
                .paymentAmount(1000)
                .paymentMethod(PaymentMethod.CARD)
                .cartItemList(cartItemList)
                .build();
    }
}