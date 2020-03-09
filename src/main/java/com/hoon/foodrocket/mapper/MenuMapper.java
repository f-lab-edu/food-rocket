package com.hoon.foodrocket.mapper;

import com.hoon.foodrocket.domain.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {
    List<Menu> getMenus(Long restaurantId);

    // 메뉴 변경(등록 또는 수정)
    void changeMenu(List<Menu> changeList);

    // 메뉴 삭제
    void deleteMenu(List<Menu> deleteList);
}
