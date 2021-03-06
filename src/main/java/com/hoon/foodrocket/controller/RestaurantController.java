package com.hoon.foodrocket.controller;

import com.hoon.foodrocket.aop.LoginType;
import com.hoon.foodrocket.aop.UserAuthorityLevel;
import com.hoon.foodrocket.service.OwnerService;
import com.hoon.foodrocket.service.RestaurantService;
import com.hoon.foodrocket.domain.Restaurant;
import com.hoon.foodrocket.util.HttpSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private OwnerService ownerService;

    @LoginType(level = UserAuthorityLevel.USER)
    @GetMapping
    public List<Restaurant> list(@RequestParam("category") String category,
                                 @RequestParam("cursorId") String cursorId,
                                 HttpSession session) {
        String loginUserEmail = HttpSessionUtil.getLoginUserEmail(session);

        return restaurantService.getRestaurantsByAddressAndCategory(category, cursorId, loginUserEmail);
    }

    @GetMapping("/{id}")
    public Restaurant detail(@PathVariable("id") Long id) {
        return restaurantService.getRestaurant(id);
    }

    @LoginType(level = UserAuthorityLevel.OWNER)
    @PostMapping
    public HttpStatus create(@RequestBody Restaurant resource, HttpSession session) {
        String loginOwnerEmail = HttpSessionUtil.getLoginOwnerEmail(session);

        restaurantService.registerRestaurant(resource, loginOwnerEmail);

        return HttpStatus.CREATED;
    }

    @LoginType(level = UserAuthorityLevel.OWNER)
    @PatchMapping("/{id}")
    public HttpStatus update(@PathVariable("id") Long id, @RequestBody Restaurant resource, HttpSession session) {
        String loginOwnerEmail = HttpSessionUtil.getLoginOwnerEmail(session);

        restaurantService.updateRestaurant(id, resource, loginOwnerEmail);

        return HttpStatus.OK;
    }

    @LoginType(level = UserAuthorityLevel.OWNER)
    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id, HttpSession session) {
        restaurantService.deleteRestaurant(id);

        return HttpStatus.OK;
    }
}
