package com.hoon.foodrocket.mapper;

import com.hoon.foodrocket.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper // Mybatis mapper 표시
public interface UserMapper {
    // 유저 조회(id)
    User getUserFromId(Long id);

    // 유저 조회(email)
    User getUserFromEmail(String email);

    // 회원가입
    void registerUser(User user);

}
