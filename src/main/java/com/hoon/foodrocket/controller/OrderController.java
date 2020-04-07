package com.hoon.foodrocket.controller;

import com.hoon.foodrocket.aop.LoginType;
import com.hoon.foodrocket.aop.UserAuthorityLevel;
import com.hoon.foodrocket.domain.order.CardOrder;
import com.hoon.foodrocket.domain.order.KakaoOrder;
import com.hoon.foodrocket.domain.order.OrderDetail;
import com.hoon.foodrocket.domain.order.OrderHistory;
import com.hoon.foodrocket.domain.order.PhoneOrder;
import com.hoon.foodrocket.service.OrderService;
import com.hoon.foodrocket.service.payment.CardPaymentProcess;
import com.hoon.foodrocket.service.payment.KakaoPaymentProcess;
import com.hoon.foodrocket.service.payment.PhonePaymentProcess;
import com.hoon.foodrocket.util.HttpSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @LoginType(level = UserAuthorityLevel.USER)
    @GetMapping
    public List<OrderHistory> list(@RequestParam("cursorId") Long cursorId, HttpSession session) {
        String loginUserEmail = HttpSessionUtil.getLoginUserEmail(session);

        return orderService.getOrders(cursorId, loginUserEmail);
    }

    @LoginType(level = UserAuthorityLevel.USER)
    @GetMapping("/{id}")
    public OrderDetail detail(@PathVariable("id") Long id, HttpSession session) {
        String loginUserEmail = HttpSessionUtil.getLoginUserEmail(session);

        return orderService.getOrderDetail(id, loginUserEmail);
    }

    @LoginType(level = UserAuthorityLevel.USER)
    @PostMapping("/payments/card")
    public HttpStatus orderByCardPayment(@RequestBody CardOrder cardOrder, HttpSession session) {
        String loginUserEmail = HttpSessionUtil.getLoginUserEmail(session);

        cardOrder.setUserEmail(loginUserEmail);
        orderService.registerOrder(cardOrder, cardOrder.getCardPayment(), loginUserEmail, new CardPaymentProcess());

        return HttpStatus.CREATED;
    }

    @LoginType(level = UserAuthorityLevel.USER)
    @PostMapping("/payments/phone")
    public HttpStatus orderByPhonePayment(@RequestBody PhoneOrder phoneOrder, HttpSession session) {
        String loginUserEmail = HttpSessionUtil.getLoginUserEmail(session);

        phoneOrder.setUserEmail(loginUserEmail);
        orderService.registerOrder(phoneOrder, phoneOrder.getPhonePayment(), loginUserEmail, new PhonePaymentProcess());

        return HttpStatus.CREATED;
    }

    @LoginType(level = UserAuthorityLevel.USER)
    @PostMapping("/payments/kakao")
    public HttpStatus orderByKakaoPayment(@RequestBody KakaoOrder kakaoOrder, HttpSession session) {
        String loginUserEmail = HttpSessionUtil.getLoginUserEmail(session);

        kakaoOrder.setUserEmail(loginUserEmail);
        orderService.registerOrder(kakaoOrder, kakaoOrder.getKakaoPayment(), loginUserEmail, new KakaoPaymentProcess());

        return HttpStatus.CREATED;
    }

    @LoginType(level = UserAuthorityLevel.OWNER)
    @PatchMapping("/{id}")
    public HttpStatus update(@PathVariable("id") Long id,
                             @RequestParam("status") String status,
                             HttpSession session) {
        orderService.updateOrderStatus(status, id);

        return HttpStatus.OK;
    }

}