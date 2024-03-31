package com.isaguler.qrcode.controller;

import com.google.zxing.WriterException;
import com.isaguler.qrcode.service.QRService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;

@RestController
@RequestMapping("/qr")
public class QRController {

    private final QRService qrService;

    public QRController(QRService qrService) {
        this.qrService = qrService;
    }

    @GetMapping(value = "/generate", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BufferedImage> generateQR(@RequestParam String input) throws WriterException {
        return new ResponseEntity<>(qrService.generateQR(input), HttpStatus.OK);
    }
}
