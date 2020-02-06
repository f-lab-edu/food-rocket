package com.hoon.foodrocket.util;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class SHA256Util {
    public static String encode(String msg) {
        String result = null;
        MessageDigest md;

        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(msg.getBytes());

            byte[] data = md.digest();

            StringBuilder sb = new StringBuilder();

            for (byte b : data) {
                sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }

            result = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("현재 환경에서 사용할 수 없는 알고리즘(SHA-256)입니다.");
        }

        return result;
    }
}
