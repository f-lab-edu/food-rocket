package com.hoon.foodrocket.aop;

import com.hoon.foodrocket.util.HttpSessionUtil;

import javax.servlet.http.HttpSession;

public class UserLoginVerification implements LoginVerificationInterface {
    @Override
    public void loginVerification(HttpSession session) {
        String loginUserEmail = HttpSessionUtil.getLoginUserEmail(session);

        if (loginUserEmail == null) {
            throw new IllegalStateException("로그인(유저)이 필요합니다.");
        }
    }
}
