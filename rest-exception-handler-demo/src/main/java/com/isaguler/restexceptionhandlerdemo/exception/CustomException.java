package com.isaguler.restexceptionhandlerdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomException extends RuntimeException {

    public CustomException(String message) {
        super("Custom Exception For Request : " + message);
    }
}
