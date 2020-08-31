package com.simple.weathermonitor.exception;

public class UserNotFoundException extends Exception {
    private static final String MESSAGE = "Failed finding user";

    public UserNotFoundException() {
        super(MESSAGE);
    }
}
