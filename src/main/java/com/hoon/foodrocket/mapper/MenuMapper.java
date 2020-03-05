package com.hoon.foodrocket.mapper;

import com.hoon.foodrocket.domain.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {
    List<Menu> getMenus(Long restaurantId);

    void insertMenu(List<Menu> addList);

    void updateMenu(List<Menu> updateList);

    void deleteMenu(List<Menu> deleteList);
}
