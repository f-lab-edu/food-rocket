package com.hoon.foodrocket.util;

import javax.servlet.http.HttpSession;

public class HttpSessionUtil {
    public static final String USER_SESSION_KEY = "loginUser";

    public static void setLoginUserEmail(HttpSession session, String email) {
        session.setAttribute(USER_SESSION_KEY, email);
    }

    public static void logout(HttpSession session) {
        session.invalidate();
    }
}
