package com.hoon.foodrocket.service;

import com.hoon.foodrocket.domain.User;
import com.hoon.foodrocket.mapper.UserMapper;
import com.hoon.foodrocket.util.SHA256Util;
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

    public User getUserFromId(Long id) {
        return userMapper.getUserFromId(id);
    }

    public User getUserFromEmail(String email) {
        return userMapper.getUserFromEmail(email);
    }

    @Transactional
    public void registerUser(User user) {
        User userFromEmail = userMapper.getUserFromEmail(user.getEmail());

        if (userFromEmail != null) {
            throw new IllegalStateException("이미 등록된 정보(유저)입니다.");
        }

        String encodedPassword = passwordEncryption(user.getPassword());
        user.setPassword(encodedPassword);

        userMapper.registerUser(user);
    }

    public User login(String email, String password) {
        User user = userMapper.getUserFromEmail(email);

        if (user == null) {
            throw new IllegalStateException("정보(유저)가 없습니다.");
        }
        
        if (user.isNotMatchPassword(passwordEncryption(password))) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return user;
    }

    public String passwordEncryption(String password){
        return SHA256Util.encode(password);
    }
}
