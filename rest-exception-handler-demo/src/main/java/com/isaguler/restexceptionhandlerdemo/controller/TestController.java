package com.isaguler.restexceptionhandlerdemo.controller;

import com.isaguler.restexceptionhandlerdemo.dto.Request;
import com.isaguler.restexceptionhandlerdemo.exception.CustomException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class TestController {

    @GetMapping("/custom")
    public ResponseEntity<Object> customExceptionDemo() {
        throw new CustomException("demo");
    }

    @PostMapping("/validation")
    public ResponseEntity<Object> validationDemo(@Valid @RequestBody Request request) {
        return new ResponseEntity<>(request + " --> is OK", HttpStatus.OK);
    }
}
