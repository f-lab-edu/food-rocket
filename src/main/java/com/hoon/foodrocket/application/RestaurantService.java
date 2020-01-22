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
            throw new IllegalStateException("가게 정보가 없습니다.");
        }

        return restaurants;
    }

    @Transactional
    public Restaurant getRestaurant(Long id) {
        Restaurant restaurant = restaurantMapper.getRestaurant(id);

        if (restaurant == null) {
            throw new IllegalStateException("가게 정보가 없습니다.");
        }

        return restaurant;
    }

    @Transactional
    public void addRestaurant(String name, String address, String ownerEmail) {
        Restaurant restaurant = Restaurant.builder()
                .name(name)
                .address(address)
                .ownerEmail(ownerEmail)
                .build();

        restaurantMapper.addRestaurant(restaurant);
    }

    @Transactional
    public void updateRestaurant(Long id, String name, String address, String loginUserEmail) {
        Restaurant restaurant = restaurantMapper.getRestaurant(id);

        if (restaurant == null) {
            throw new IllegalStateException("가게 정보가 없습니다.");
        }

        if (!restaurant.matchOwnerEmail(loginUserEmail)) {
            throw new IllegalStateException("본인 가게 정보만 수정할 수 있습니다.");
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
            throw new IllegalStateException("가게 정보가 없습니다.");
        }

        restaurantMapper.deleteRestaurant(id);
    }
}
