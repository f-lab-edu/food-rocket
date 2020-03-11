package com.hoon.foodrocket.aop;

import javax.servlet.http.HttpSession;

public interface LoginVerificationInterface {
    void loginVerification(HttpSession session);
}