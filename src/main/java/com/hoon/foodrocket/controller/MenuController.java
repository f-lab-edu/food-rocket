package com.hoon.foodrocket.controller;

import com.hoon.foodrocket.aop.LoginType;
import com.hoon.foodrocket.aop.Type;
import com.hoon.foodrocket.application.MenuService;
import com.hoon.foodrocket.domain.Menu;
import com.hoon.foodrocket.util.HttpSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/restaurants/{restaurantId}/menus")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping
    public List<Menu> list(@PathVariable("restaurantId") Long restaurantId) {
        return menuService.getMenus(restaurantId);
    }

    @LoginType(type = Type.OWNER)
    @PatchMapping
    public HttpStatus update(@PathVariable("restaurantId") Long restaurantId, @RequestBody Map<String, List<Menu>> menuMap, HttpSession session) {
        String loginOwnerEmail = HttpSessionUtil.getLoginOwnerEmail(session);

        List<Menu> changeList = menuMap.get("change");
        List<Menu> deleteList = menuMap.get("delete");

        menuService.updateMenu(restaurantId, changeList, deleteList, loginOwnerEmail);

        return HttpStatus.OK;
    }

}
