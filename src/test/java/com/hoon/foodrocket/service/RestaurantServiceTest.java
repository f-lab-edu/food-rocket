package com.hoon.foodrocket.service;

import com.hoon.foodrocket.domain.Restaurant;
import com.hoon.foodrocket.mapper.MenuMapper;
import com.hoon.foodrocket.mapper.RestaurantMapper;
import com.hoon.foodrocket.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RestaurantServiceTest {

    @InjectMocks
    private RestaurantService restaurantService;

    @Mock
    private RestaurantMapper restaurantMapper;

    @Mock
    private UserMapper userMapper;

    @Mock
    private MenuMapper menuMapper;

    @Test
    public void 가게목록_성공() throws Exception {
        // given
        String region = "금오동";
        String category = "한식";
        String cursorId = "10";

        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(generateRestaurant());

        given(restaurantMapper.getRestaurantsByAddressAndCategory(any(), any(), any())).willReturn(restaurants);

        // when
        List<Restaurant> restaurantsByAddressAndCategory = restaurantService.getRestaurantsByAddressAndCategory(region, category, cursorId);

        // then
        assertEquals(restaurants.get(0).getName(), restaurantsByAddressAndCategory.get(0).getName());
        assertEquals(restaurants.size(), restaurantsByAddressAndCategory.size());
    }

    @Test
    public void 가게상세_성공() throws Exception {
        // given
        Restaurant restaurant = generateRestaurant();
        given(restaurantMapper.getRestaurant(any())).willReturn(restaurant);

        // when
        Restaurant result = restaurantService.getRestaurant(any());

        // then
        verify(menuMapper).getMenus(any());
        assertEquals(restaurant, result);
    }

    @Test(expected = IllegalStateException.class)
    public void 가게상세_실패_가게정보_없음() throws Exception {
        // given
        given(restaurantMapper.getRestaurant(any())).willReturn(null);

        // when
        restaurantService.getRestaurant(any());

        // then
        verify(menuMapper, never()).getMenus(any());
        fail("예외가 발생해야 한다.");
    }

    @Test
    public void 가게등록_성공() throws Exception {
        // given
        String loginOwnerEmail = "test@example.com";

        Restaurant restaurant = generateRestaurant();
        given(restaurantMapper.getNumberOfRestaurants(any())).willReturn(0);

        // when
        restaurantService.registerRestaurant(restaurant, loginOwnerEmail);

        // then
        verify(restaurantMapper).insertRestaurant(any());
    }

    @Test(expected = IllegalStateException.class)
    public void 가게등록_실패_횟수초과() throws Exception {
        // given
        String loginOwnerEmail = "test@example.com";

        Restaurant restaurant = generateRestaurant();
        given(restaurantMapper.getNumberOfRestaurants(any())).willReturn(10);

        // when
        restaurantService.registerRestaurant(restaurant, loginOwnerEmail);

        // then
        verify(restaurantMapper, never()).insertRestaurant(any());
        fail("예외가 발생해야 한다.");
    }

    @Test
    public void 가게수정_성공() throws Exception {
        // given
        String loginOwnerEmail = "test@example.com";

        Restaurant restaurant = generateRestaurant();
        given(restaurantMapper.getRestaurant(any())).willReturn(restaurant);

        // when
        restaurantService.updateRestaurant(any(), restaurant, loginOwnerEmail);

        // then
        verify(restaurantMapper).updateRestaurant(any());
    }

    @Test(expected = IllegalStateException.class)
    public void 가게수정_실패_가게정보_없음() throws Exception {
        // given
        String loginOwnerEmail = "test@example.com";

        Restaurant restaurant = generateRestaurant();
        given(restaurantMapper.getRestaurant(any())).willReturn(null);

        // when
        restaurantService.updateRestaurant(any(), restaurant, loginOwnerEmail);

        // then
        verify(restaurantMapper, never()).updateRestaurant(any());
        fail("예외가 발생해야 한다.");
    }

    @Test(expected = IllegalStateException.class)
    public void 가게수정_실패_이메일_불일치() throws Exception {
        // given
        String loginOwnerEmail = "xxx@example.com";

        Restaurant restaurant = generateRestaurant();
        given(restaurantMapper.getRestaurant(any())).willReturn(restaurant);

        // when
        restaurantService.updateRestaurant(any(), restaurant, loginOwnerEmail);

        // then
        verify(restaurantMapper, never()).updateRestaurant(any());
        fail("예외가 발생해야 한다.");
    }

    @Test
    public void 가게삭제_성공() throws Exception {
        // given
        Restaurant restaurant = generateRestaurant();
        given(restaurantMapper.getRestaurant(any())).willReturn(restaurant);

        // when
        restaurantService.deleteRestaurant(any());

        // then
        verify(restaurantMapper).deleteRestaurant(any());
    }

    @Test(expected = IllegalStateException.class)
    public void 가게삭제_실패_가게정보_없음() throws Exception {
        // given
        given(restaurantMapper.getRestaurant(any())).willReturn(null);

        // when
        restaurantService.deleteRestaurant(any());

        // then
        verify(restaurantMapper, never()).deleteRestaurant(any());
        fail("예외가 발생해야 한다.");
    }

    private Restaurant generateRestaurant() {
        return Restaurant.builder()
                .name("테스트")
                .address("주소")
                .ownerEmail("test@example.com")
                .information("테스트입니다.")
                .phoneNumber("010-1111-2222")
                .openTime("09:00")
                .closeTime("18:00")
                .category("한식")
                .build();
    }
}