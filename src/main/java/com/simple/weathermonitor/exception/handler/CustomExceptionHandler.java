package com.simple.weathermonitor.exception.handler;

import com.simple.weathermonitor.exception.CitySearchException;
import com.simple.weathermonitor.exception.SaveObservationException;
import com.simple.weathermonitor.exception.SaveUserException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {SaveUserException.class, SaveObservationException.class, CitySearchException.class})
    protected ResponseEntity<Object> handleConflict(SaveUserException ex, WebRequest request) {
        String message = ex.getMessage();
        if (ex.getCause() != null) {
            message = message + ": " + ex.getCause().getMessage();
        }
        Map<String, Object> body = Map.of("error", message, "date", LocalDateTime.now());
        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
