package com.hoon.foodrocket.aop;

import javax.servlet.http.HttpSession;

public interface LoginVerificationService {
    void ownerLoginCheck(HttpSession session);
    void userLoginCheck(HttpSession session);
}
