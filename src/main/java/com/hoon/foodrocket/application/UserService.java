package com.hoon.foodrocket.application;

import com.hoon.foodrocket.domain.User;
import com.hoon.foodrocket.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
  Transactional
  기존에 try-catch 구문을 통해 커밋과 롤백을 직접 처리해줬어야 했다.
  이를 @Transactional 어노테이션을 통해 자동으로 관리해줄 수 있으며
  if, try, catch 와 같이 중복된 코드가 줄어들어 가독성을 높일 수 있다.
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    @Transactional
    public User getUser(Long id) {
        User user = userMapper.getUserWithId(id);

        if (user == null) {
            throw new IllegalStateException("User is not existed");
        }

        return user;
    }

    @Transactional
    public void registerUser(String email, String name, String password) {
        User user = userMapper.getUserWithEmail(email);

        if (user != null) {
            throw new IllegalStateException("User is already registered");
        }

        User builder = User.builder()
                .email(email)
                .name(name)
                .password(password)
                .build();

        userMapper.registerUser(builder);
    }

    public User login(String email, String password) {
        User user = userMapper.getUserWithEmail(email);

        if (user == null) {
            throw new IllegalStateException("User is not existed");
        }

        if (!password.equals(user.getPassword())) {
            throw new IllegalStateException("Password is wrong");
        }

        return user;
    }
}
