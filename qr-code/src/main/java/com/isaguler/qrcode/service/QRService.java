package com.isaguler.qrcode.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.isaguler.qrcode.util.ValidationUtil;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

@Service
public class QRService {

    private final ValidationUtil validationUtil;

    public QRService(ValidationUtil validationUtil) {
        this.validationUtil = validationUtil;
    }

    public BufferedImage generateQR(String input) throws WriterException {
        validationUtil.validateUrlInput(input);

        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = barcodeWriter.encode(input, BarcodeFormat.QR_CODE, 200, 200);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
}
