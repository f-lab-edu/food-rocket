package com.hoon.foodrocket.controller;

import com.hoon.foodrocket.application.RestaurantService;
import com.hoon.foodrocket.domain.Restaurant;
import com.hoon.foodrocket.util.HttpSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public List<Restaurant> list() {
        return restaurantService.getRestaurants();
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id) {
        return restaurantService.getRestaurant(id);
    }

    @PostMapping("/restaurants")
    public ResponseEntity<?> create(@RequestBody Restaurant resource, HttpSession session) {
        String loginUserEmail = HttpSessionUtil.getLoginUserEmail(session);

        if (loginUserEmail == null) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }

        String name = resource.getName();
        String address = resource.getAddress();
        String ownerEmail = resource.getOwnerEmail();

        restaurantService.addRestaurant(name, address, ownerEmail);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/restaurants/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Restaurant resource, HttpSession session) {
        String loginUserEmail = HttpSessionUtil.getLoginUserEmail(session);

        if (loginUserEmail == null) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }

        String name = resource.getName();
        String address = resource.getAddress();

        restaurantService.updateRestaurant(id, name, address, loginUserEmail);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/restaurants/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        restaurantService.deleteRestaurant(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
