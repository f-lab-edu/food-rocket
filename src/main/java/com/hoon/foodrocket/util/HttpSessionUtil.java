package com.hoon.foodrocket.util;

import javax.servlet.http.HttpSession;

public class HttpSessionUtil {
    public static final String USER_SESSION_KEY = "loginUser";
    public static final String OWNER_SESSION_KEY = "loginOwner";

    public static void setLoginUserEmail(HttpSession session, String email) {
        session.setAttribute(USER_SESSION_KEY, email);
    }

    public static String getLoginUserEmail(HttpSession session) {
        return (String) session.getAttribute(USER_SESSION_KEY);
    }

    public static void setLoginOwnerEmail(HttpSession session, String email) {
        session.setAttribute(OWNER_SESSION_KEY, email);
    }

    public static String getLoginOwnerEmail(HttpSession session) {
        return (String) session.getAttribute(OWNER_SESSION_KEY);
    }

    public static void logout(HttpSession session) {
        session.invalidate();
    }
}
