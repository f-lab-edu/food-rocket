package com.hoon.foodrocket.application;

import com.hoon.foodrocket.domain.Menu;
import com.hoon.foodrocket.domain.Restaurant;
import com.hoon.foodrocket.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RestaurantService restaurantMapper;

    @Transactional
    public List<Menu> getMenus(Long restaurantId) {
        return menuMapper.getMenus(restaurantId);
    }

    @Transactional
    public void updateMenu(Long restaurantId, List<Menu> menus, String loginOwnerEmail) {
        Restaurant restaurant = restaurantMapper.getRestaurant(restaurantId);

        if (restaurant == null) {
            throw new IllegalStateException("가게 정보가 없습니다.");
        }

        if (restaurant.isNotMatchOwnerEmail(loginOwnerEmail)) {
            throw new IllegalStateException("본인 가게 메뉴만 수정할 수 있습니다.");
        }

        for (Menu menu : menus) {
            if (menu.isDestroy()) {
                menuMapper.deleteMenu(menu.getId());
            } else {
                if (menu.getId() != null) {
                    menuMapper.updateMenu(menu);
                } else {
                    menu.setDestroy(false);
                    menuMapper.insertMenu(menu);
                }
            }
        }
    }
}
