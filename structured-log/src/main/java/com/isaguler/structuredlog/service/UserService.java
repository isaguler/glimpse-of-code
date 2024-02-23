package com.isaguler.structuredlog.service;

import com.isaguler.structuredlog.model.UserModel;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @PostConstruct
    public void testLog() {
        UserModel userModel = new UserModel(UUID.randomUUID().toString(), "isa", "12345");

        Marker marker = MarkerFactory.getMarker("USER");
        Marker marker1 = MarkerFactory.getMarker("ERROR");

        logger.atInfo()
                .addKeyValue("user_info", userModel)
                .addMarker(marker)
                .addMarker(marker1)
                .log();

        try {
            String s = null;
            s.substring(0, 1);
        } catch (Exception e) {
            Marker marker2 = MarkerFactory.getMarker("EXCEPTION");
            logger.atError()
                    .addMarker(marker2)
                    .addKeyValue("error_message", e.getMessage())
                    .addKeyValue("exception_class", e.getClass())
                    .log();
        }
    }
}
