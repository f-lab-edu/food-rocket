package com.hoon.foodrocket.application;

import com.hoon.foodrocket.domain.User;
import com.hoon.foodrocket.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional  // 메소드나 클래스에 트랜잭션 속성을 설명
public class UserService {

    UserMapper userMapper;

    PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUser(String email) {
        User existed = userMapper.getUser(email);

        if (existed == null) {
            throw new EmailNotExistedException(email);
        }

        return existed;
    }

    public void registerUser(String email, String name, String password) {
        User existed = userMapper.getUser(email);

        if (existed != null) {
            throw new EmailExistedException(email);
        }

        String encodedPassword = passwordEncoder.encode(password);

        User user = User.builder()
                .email(email)
                .name(name)
                .password(encodedPassword)
                .build();

        userMapper.registerUser(user);
    }

}
