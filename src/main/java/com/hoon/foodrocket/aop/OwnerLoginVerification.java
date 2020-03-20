package com.hoon.foodrocket.aop;

import com.hoon.foodrocket.util.HttpSessionUtil;

import javax.servlet.http.HttpSession;

public class OwnerLoginVerification implements LoginVerificationInterface {
    @Override
    public void loginVerification(HttpSession session) {
        String loginOwnerEmail = HttpSessionUtil.getLoginOwnerEmail(session);

        if (loginOwnerEmail == null) {
            throw new IllegalStateException("로그인(사장)이 필요합니다.");
        }
    }
}
