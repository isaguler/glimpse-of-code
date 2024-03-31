package com.isaguler.qrcode.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleException(Exception e) {
        return new ResponseEntity<>("Technical Exception...!!! Exception is : " + e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InputNotValidException.class})
    public ResponseEntity<Object> handleException(InputNotValidException e) {
        return new ResponseEntity<>("Your input is not a valid URL" + e, HttpStatus.NOT_ACCEPTABLE);
    }
}
