package com.hoon.foodrocket.service;

import com.hoon.foodrocket.domain.Menu;
import com.hoon.foodrocket.domain.Restaurant;
import com.hoon.foodrocket.mapper.MenuMapper;
import com.hoon.foodrocket.mapper.RestaurantMapper;
import com.hoon.foodrocket.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestaurantService {
    public static final int MAX_NUMBER_RESTAURANT = 3;

    @Autowired
    private RestaurantMapper restaurantMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 유저의 지역 정보와 유저가 선택한 음식 카테고리를 통해 가게 목록을 보여준다.
     *
     * @param category
     * @param cursorId
     * @param loginUserEmail
     * @return
     */
    @Transactional
    public List<Restaurant> getRestaurantsByAddressAndCategory(String category, String cursorId, String loginUserEmail) {
        String region = userMapper.getRegion(loginUserEmail);

        return restaurantMapper.getRestaurantsByAddressAndCategory(region, category, cursorId);
    }

    public Restaurant getRestaurant(Long id) {
        Restaurant restaurant = restaurantMapper.getRestaurant(id);

        if (restaurant == null) {
            throw new IllegalStateException("가게 정보가 없습니다.");
        }

        List<Menu> menus = menuMapper.getMenus(id);
        restaurant.setMenus(menus);

        return restaurant;
    }

    @Transactional
    public void registerRestaurant(Restaurant resource, String loginOwnerEmail) {
        int count = restaurantMapper.getNumberOfRestaurants(loginOwnerEmail);
        if (count >= MAX_NUMBER_RESTAURANT) {
            throw new IllegalStateException("가게는 최대 3곳까지만 등록할 수 있습니다.");
        }

        resource.setOwnerEmail(loginOwnerEmail);
        restaurantMapper.insertRestaurant(resource);
    }

    @Transactional
    public void updateRestaurant(Long id, Restaurant resource, String loginOwnerEmail) {
        Restaurant restaurant = restaurantMapper.getRestaurant(id);

        if (restaurant == null) {
            throw new IllegalStateException("가게 정보가 없습니다.");
        }

        if (restaurant.isNotMatchOwnerEmail(loginOwnerEmail)) {
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

    public int getNumberOfRestaurants(String loginOwnerEmail) {
        return restaurantMapper.getNumberOfRestaurants(loginOwnerEmail);
    }
}
