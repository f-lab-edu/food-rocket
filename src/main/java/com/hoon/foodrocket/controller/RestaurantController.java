package com.hoon.foodrocket.controller;

import com.hoon.foodrocket.application.RestaurantService;
import com.hoon.foodrocket.domain.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> create(@RequestBody Restaurant resource) {
        String name = resource.getName();
        String address = resource.getAddress();

        restaurantService.addRestaurant(name, address);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/restaurants/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Restaurant resource) {
        String name = resource.getName();
        String address = resource.getAddress();

        restaurantService.updateRestaurant(id, name, address);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/restaurants/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        restaurantService.deleteRestaurant(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
