package com.hoon.foodrocket.application;

import com.hoon.foodrocket.domain.User;
import com.hoon.foodrocket.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
/*
  기존에 try-catch 구문을 통해 커밋과 롤백을 직접 처리해줬어야 했다.
  이를 @Transactional 어노테이션을 통해 자동으로 관리해줄 수 있으며
  if, try, catch 와 같이 중복된 코드가 줄어들어 가독성을 높일 수 있다.
 */
@Transactional
public class UserService {

    UserMapper userMapper;

    PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUser(String email) {
        User user = userMapper.getUser(email);

        if (user == null) {
            throw new IllegalStateException("Email is not existed: " + email);
        }

        return user;
    }

    public void registerUser(String email, String name, String password) {
        User user = userMapper.getUser(email);

        if (user != null) {
            throw new IllegalStateException("Email is already existed: " + email);
        }

        String encodedPassword = passwordEncoder.encode(password);

        User builder = User.builder()
                .email(email)
                .name(name)
                .password(encodedPassword)
                .build();

        userMapper.registerUser(builder);
    }
    
}
