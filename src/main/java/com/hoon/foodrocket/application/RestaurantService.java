package com.hoon.foodrocket.application;

import com.hoon.foodrocket.domain.Restaurant;
import com.hoon.foodrocket.mapper.RestaurantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantMapper restaurantMapper;

    @Transactional
    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants = restaurantMapper.findAll();

        if (restaurants == null) {
            throw new IllegalStateException("Restaurant is not found");
        }

        return restaurants;
    }

    @Transactional
    public Restaurant getRestaurant(Long id) {
        Restaurant restaurant = restaurantMapper.getRestaurant(id);

        if (restaurant == null) {
            throw new IllegalStateException("Restaurant is not found");
        }

        return restaurant;
    }

    @Transactional
    public void addRestaurant(String name, String address) {
        Restaurant restaurant = Restaurant.builder()
                .name(name)
                .address(address)
                .build();

        restaurantMapper.addRestaurant(restaurant);
    }

    @Transactional
    public void updateRestaurant(Long id, String name, String address) {
        Restaurant restaurant = restaurantMapper.getRestaurant(id);

        if (restaurant == null) {
            throw new IllegalStateException("Restaurant is not found");
        }

        Restaurant builder = Restaurant.builder()
                .id(id)
                .name(name)
                .address(address)
                .build();

        restaurantMapper.updateRestaurant(builder);
    }

    public void deleteRestaurant(Long id) {
        Restaurant restaurant = restaurantMapper.getRestaurant(id);

        if (restaurant == null) {
            throw new IllegalStateException("Restaurant is not found");
        }

        restaurantMapper.deleteRestaurant(id);
    }
}
