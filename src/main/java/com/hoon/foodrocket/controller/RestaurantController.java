package com.hoon.foodrocket.controller;

import com.hoon.foodrocket.application.OwnerService;
import com.hoon.foodrocket.application.RestaurantService;
import com.hoon.foodrocket.domain.Owner;
import com.hoon.foodrocket.domain.Restaurant;
import com.hoon.foodrocket.util.HttpSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    public static final int MAX_NUMBER_RESTAURANT = 3;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private OwnerService ownerService;

    @GetMapping
    public List<Restaurant> list() {
        return restaurantService.getRestaurants();
    }

    @GetMapping("/{id}")
    public Restaurant detail(@PathVariable("id") Long id) {
        return restaurantService.getRestaurant(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Restaurant resource, HttpSession session) {
        String loginOwnerEmail = HttpSessionUtil.getLoginOwnerEmail(session);

        if (loginOwnerEmail == null) {
            throw new IllegalStateException("로그인(사장)이 필요합니다.");
        }

        int count = restaurantService.getNumberOfRestaurants(loginOwnerEmail);
        if (count >= MAX_NUMBER_RESTAURANT) {
            throw new IllegalStateException("가게는 최대 3곳까지만 등록할 수 있습니다.");
        }

        resource.setOwnerEmail(loginOwnerEmail);
        restaurantService.registerRestaurant(resource);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Restaurant resource, HttpSession session) {
        String loginOwnerEmail = HttpSessionUtil.getLoginOwnerEmail(session);

        if (loginOwnerEmail == null) {
            throw new IllegalStateException("로그인(사장)이 필요합니다.");
        }

        restaurantService.updateRestaurant(id, resource, loginOwnerEmail);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id, HttpSession session) {
        String loginOwnerEmail = HttpSessionUtil.getLoginOwnerEmail(session);

        if (loginOwnerEmail == null) {
            throw new IllegalStateException("로그인(사장)이 필요합니다.");
        }

        restaurantService.deleteRestaurant(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
