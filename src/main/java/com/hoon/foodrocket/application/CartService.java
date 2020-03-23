package com.hoon.foodrocket.application;

import com.hoon.foodrocket.domain.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CartService {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Resource(name = "redisTemplate")
    private ListOperations<String, Object> listOperations;

    public void registerItem(CartItem cartItem, String loginUserEmail) {
        listOperations.leftPush(loginUserEmail, cartItem);
    }

    public List<Object> getList(String loginUserEmail) {
        Long size = listOperations.size(loginUserEmail);

        return listOperations.range(loginUserEmail, 0, size);
    }

    public void deleteItem(Long id, String loginUserEmail) {
        Object value = listOperations.index(loginUserEmail, id);

        if (value == null) {
            throw new IllegalStateException("아이템 정보가 없습니다.");
        }

        listOperations.remove(loginUserEmail, 1, value);
    }

    public void clearItem(String loginUserEmail) {
        redisTemplate.delete(loginUserEmail);
    }
}
