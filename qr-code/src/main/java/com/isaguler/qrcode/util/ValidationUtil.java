package com.isaguler.qrcode.util;

import com.isaguler.qrcode.exception.InputNotValidException;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

@Service
public class ValidationUtil {

    public void validateUrlInput(String input) {
        try {
            new URL(input).toURI();
        } catch (MalformedURLException | URISyntaxException e) {
            throw new InputNotValidException(input + "-->" + e.getMessage());
        }
    }
}
