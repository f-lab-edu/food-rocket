package com.hoon.foodrocket.config;

import com.hoon.foodrocket.aop.LoginVerificationInterface;
import com.hoon.foodrocket.aop.OwnerLoginVerification;
import com.hoon.foodrocket.aop.UserAuthorityLevel;
import com.hoon.foodrocket.aop.UserLoginVerification;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
class CollectionConfig {
    @Bean
    public Map<UserAuthorityLevel, LoginVerificationInterface> map() {
        Map<UserAuthorityLevel, LoginVerificationInterface> map = new HashMap<>();

        map.put(UserAuthorityLevel.OWNER, new OwnerLoginVerification());
        map.put(UserAuthorityLevel.USER, new UserLoginVerification());

        return map;
    }
}
