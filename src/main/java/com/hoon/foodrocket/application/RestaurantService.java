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
    public List<Restaurant> getRestaurantsByAddress(String region) {
        List<Restaurant> restaurants = restaurantMapper.getRestaurantsByAddress(region);

        if (restaurants.size() == 0) {
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
    public void registerRestaurant(Restaurant resource) {
        restaurantMapper.registerRestaurant(resource);
    }

    @Transactional
    public void updateRestaurant(Long id, Restaurant resource, String loginOwnerEmail) {
        Restaurant restaurant = restaurantMapper.getRestaurant(id);

        if (restaurant == null) {
            throw new IllegalStateException("가게 정보가 없습니다.");
        }

        if (!restaurant.isMatchOwnerEmail(loginOwnerEmail)) {
            throw new IllegalStateException("본인 가게 정보만 수정할 수 있습니다.");
        }

        resource.setId(id);

        restaurantMapper.updateRestaurant(resource);
    }

    @Transactional
    public void deleteRestaurant(Long id) {
        Restaurant restaurant = restaurantMapper.getRestaurant(id);

        if (restaurant == null) {
            throw new IllegalStateException("가게 정보가 없습니다.");
        }

        restaurantMapper.deleteRestaurant(id);
    }

    @Transactional
    public int getNumberOfRestaurants(String loginOwnerEmail) {
        return restaurantMapper.getNumberOfRestaurants(loginOwnerEmail);
    }
}
