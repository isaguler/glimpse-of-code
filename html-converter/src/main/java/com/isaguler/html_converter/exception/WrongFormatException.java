package com.isaguler.html_converter.exception;

public class WrongFormatException extends RuntimeException{

    public WrongFormatException(String message) {
        super(message);
    }

    public WrongFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
