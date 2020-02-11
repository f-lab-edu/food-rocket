package com.hoon.foodrocket.controller;

import com.hoon.foodrocket.aop.CheckOwnerLogin;
import com.hoon.foodrocket.application.OwnerService;
import com.hoon.foodrocket.application.RestaurantService;
import com.hoon.foodrocket.domain.Restaurant;
import com.hoon.foodrocket.util.HttpSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private OwnerService ownerService;

    @GetMapping
    public List<Restaurant> list(@RequestParam("region") String region, @RequestParam("category") String category) {
        return restaurantService.getRestaurantsByAddressAndCategory(region, category);
    }

    @GetMapping("/{id}")
    public Restaurant detail(@PathVariable("id") Long id) {
        return restaurantService.getRestaurant(id);
    }

    @CheckOwnerLogin
    @PostMapping
    public HttpStatus create(@RequestBody Restaurant resource, HttpSession session) {
        String loginOwnerEmail = HttpSessionUtil.getLoginOwnerEmail(session);

        restaurantService.registerRestaurant(resource, loginOwnerEmail);

        return HttpStatus.CREATED;
    }

    @CheckOwnerLogin
    @PatchMapping("/{id}")
    public HttpStatus update(@PathVariable("id") Long id, @RequestBody Restaurant resource, HttpSession session) {
        String loginOwnerEmail = HttpSessionUtil.getLoginOwnerEmail(session);

        restaurantService.updateRestaurant(id, resource, loginOwnerEmail);

        return HttpStatus.OK;
    }

    @CheckOwnerLogin
    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id, HttpSession session) {
        restaurantService.deleteRestaurant(id);

        return HttpStatus.OK;
    }
}
