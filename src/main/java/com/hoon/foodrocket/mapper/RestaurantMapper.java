package com.hoon.foodrocket.mapper;

import com.hoon.foodrocket.domain.Restaurant;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RestaurantMapper {
    // 가게 목록
    List<Restaurant> getRestaurantsByAddressAndCategory(String region, String category);

    // 가게 조회
    Restaurant getRestaurant(Long id);

    // 가게 등록
    void registerRestaurant(Restaurant restaurant);

    // 가게 수정
    void updateRestaurant(Restaurant restaurant);

    // 가게 삭제
    void deleteRestaurant(Long id);

    // 사장이 등록한 가게 수
    int getNumberOfRestaurants(String loginOwnerEmail);
}
