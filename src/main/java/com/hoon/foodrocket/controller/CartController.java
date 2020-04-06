package com.hoon.foodrocket.controller;

import com.hoon.foodrocket.aop.LoginType;
import com.hoon.foodrocket.aop.UserAuthorityLevel;
import com.hoon.foodrocket.service.CartService;
import com.hoon.foodrocket.domain.CartItem;
import com.hoon.foodrocket.util.HttpSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {
    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @LoginType(level = UserAuthorityLevel.USER)
    @PostMapping
    public HttpStatus create(@RequestBody CartItem cartItem, HttpSession session) {
        String loginUserEmail = HttpSessionUtil.getLoginUserEmail(session);

        cartService.registerItem(cartItem, loginUserEmail);

        return HttpStatus.CREATED;
    }

    @LoginType(level = UserAuthorityLevel.USER)
    @GetMapping
    public List<Object> list(HttpSession session) {
        String loginUserEmail = HttpSessionUtil.getLoginUserEmail(session);

        return cartService.getList(loginUserEmail);
    }

    @LoginType(level = UserAuthorityLevel.USER)
    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id, HttpSession session) {
        String loginUserEmail = HttpSessionUtil.getLoginUserEmail(session);

        cartService.deleteItem(id, loginUserEmail);

        return HttpStatus.OK;
    }

    @LoginType(level = UserAuthorityLevel.USER)
    @DeleteMapping("/clear")
    public HttpStatus clear(HttpSession session) {
        String loginUserEmail = HttpSessionUtil.getLoginUserEmail(session);

        cartService.clearItem(loginUserEmail);

        return HttpStatus.OK;
    }
}
