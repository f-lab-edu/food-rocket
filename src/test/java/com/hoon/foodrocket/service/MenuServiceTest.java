package com.hoon.foodrocket.service;

import com.hoon.foodrocket.domain.Menu;
import com.hoon.foodrocket.domain.Restaurant;
import com.hoon.foodrocket.mapper.MenuMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MenuServiceTest {
    String loginOwnerEmail = "test@example.com";
    List<Menu> changeList, deleteList;

    @InjectMocks
    private MenuService menuService;

    @Mock
    private MenuMapper menuMapper;

    @Mock
    private RestaurantService restaurantMapper;

    @Before
    public void init() {
        Menu menu = Menu.builder()
                .name("menu")
                .price("1000")
                .restaurantId(1L)
                .build();

        changeList = new ArrayList<>();
        deleteList = new ArrayList<>();
        changeList.add(menu);
        deleteList.add(menu);
    }

    @Test
    public void 메뉴수정_성공() throws Exception {
        // given
        Restaurant restaurant = generateRestaurant();
        given(restaurantMapper.getRestaurant(any())).willReturn(restaurant);

        // when
        menuService.updateMenu(1L, changeList, deleteList, loginOwnerEmail);

        // then
        verify(menuMapper).changeMenu(any());
        verify(menuMapper).deleteMenu(any());
    }

    @Test(expected = IllegalStateException.class)
    public void 메뉴수정_실패_가게정보_없음() throws Exception {
        // given
        given(restaurantMapper.getRestaurant(any())).willReturn(null);

        // when
        menuService.updateMenu(1L, changeList, deleteList, loginOwnerEmail);

        // then
        verify(menuMapper, never()).changeMenu(any());
        verify(menuMapper, never()).deleteMenu(any());
        fail("예외가 발생해야 한다.");
    }

    @Test(expected = IllegalStateException.class)
    public void 메뉴수정_실패_이메일_불일치() throws Exception {
        // given
        String failEmail = "xxx@example.com";
        Restaurant restaurant = generateRestaurant();
        given(restaurantMapper.getRestaurant(any())).willReturn(restaurant);

        // when
        menuService.updateMenu(1L, changeList, deleteList, failEmail);

        // then
        verify(menuMapper, never()).changeMenu(any());
        verify(menuMapper, never()).deleteMenu(any());
        fail("예외가 발생해야 한다.");
    }

    private Restaurant generateRestaurant() {
        return Restaurant.builder()
                .ownerEmail("test@example.com")
                .build();
    }
}