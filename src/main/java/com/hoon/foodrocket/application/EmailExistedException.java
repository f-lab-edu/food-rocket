package com.hoon.foodrocket.application;

public class EmailExistedException extends IllegalStateException {
    EmailExistedException(String email) {
        super("Email is already registered: " + email);
    }
}
