package com.simple.weathermonitor.exception;

public class SaveUserException extends Exception {

    private static final String MESSAGE = "Failed saving user";

    public SaveUserException(Throwable cause) {
        super(MESSAGE, cause);
    }
}
