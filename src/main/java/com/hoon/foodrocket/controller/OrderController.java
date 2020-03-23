package com.hoon.foodrocket.controller;

import com.hoon.foodrocket.aop.LoginType;
import com.hoon.foodrocket.aop.UserAuthorityLevel;
import com.hoon.foodrocket.application.OrderService;
import com.hoon.foodrocket.domain.Order;
import com.hoon.foodrocket.domain.OrderHistory;
import com.hoon.foodrocket.util.HttpSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @LoginType(level = UserAuthorityLevel.USER)
    @GetMapping
    public List<OrderHistory> list(HttpSession session) {
        String loginUserEmail = HttpSessionUtil.getLoginUserEmail(session);

        return orderService.getOrders(loginUserEmail);
    }

    @LoginType(level = UserAuthorityLevel.USER)
    @PostMapping
    public HttpStatus create(@RequestBody Order order, HttpSession session) {
        String loginUserEmail = HttpSessionUtil.getLoginUserEmail(session);

        order.setUserEmail(loginUserEmail);
        orderService.registerOrder(order, loginUserEmail);

        return HttpStatus.CREATED;
    }
}