package com.isaguler.html_converter.exception;

public class PdfFileNotExistException extends RuntimeException{

    public PdfFileNotExistException(String message) {
        super(message);
    }
}
