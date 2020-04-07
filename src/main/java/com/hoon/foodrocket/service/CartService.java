package com.hoon.foodrocket.service;

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
        String cartKey = generateCartKey(loginUserEmail);
        listOperations.leftPush(cartKey, cartItem);
    }

    public List<Object> getList(String loginUserEmail) {
        String cartKey = generateCartKey(loginUserEmail);
        Long size = listOperations.size(cartKey);

        return listOperations.range(cartKey, 0, size);
    }

    public void deleteItem(Long id, String loginUserEmail) {
        String cartKey = generateCartKey(loginUserEmail);
        Object value = listOperations.index(cartKey, id);

        if (value == null) {
            throw new IllegalArgumentException("아이템 정보가 없습니다.");
        }

        listOperations.remove(cartKey, 1, value);
    }

    public void clearItem(String loginUserEmail) {
        String cartKey = generateCartKey(loginUserEmail);
        redisTemplate.delete(cartKey);
    }

    public String generateCartKey(String email) {
        return "cart:" + email;
    }
}
