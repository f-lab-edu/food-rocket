package com.hoon.foodrocket.application;

public class EmailNotExistedException extends IllegalStateException {
    EmailNotExistedException(String email) {
        super("Email is not existed: " + email);
    }
}