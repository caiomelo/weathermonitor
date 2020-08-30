package com.simple.weathermonitor.exception;

public class CitySearchException extends Exception {

    private static final String MESSAGE = "Failed searching for city";

    public CitySearchException() {
        super(MESSAGE);
    }

    public CitySearchException(Throwable cause) {
        super(MESSAGE, cause);
    }
}
