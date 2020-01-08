package com.hoon.foodrocket.mapper;

import com.hoon.foodrocket.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper // Mybatis mapper 표시
public interface UserMapper {
    User getUser(String email);

    void registerUser(User user);
}
