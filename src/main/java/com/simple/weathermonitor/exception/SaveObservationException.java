package com.simple.weathermonitor.exception;

public class SaveObservationException extends Exception {

    private static final String MESSAGE = "Failed saving observation period";

    public SaveObservationException(Throwable cause) {
        super(MESSAGE, cause);
    }

}
