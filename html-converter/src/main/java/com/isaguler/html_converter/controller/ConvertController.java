package com.isaguler.html_converter.controller;

import com.isaguler.html_converter.service.HtmlToImage;
import com.isaguler.html_converter.service.HtmlToPdf;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping
public class ConvertController {

    private final HtmlToPdf htmlToPdf;
    private final HtmlToImage htmlToImage;

    public ConvertController(HtmlToPdf htmlToPdf, HtmlToImage htmlToImage) {
        this.htmlToPdf = htmlToPdf;
        this.htmlToImage = htmlToImage;
    }

    @PostMapping(value = "/pdf", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, produces = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Object> htmlToPdf(@RequestParam MultipartFile multipartFile) {

        byte[] convert = htmlToPdf.convert(multipartFile);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "output.pdf");

        return new ResponseEntity<>(convert, headers, HttpStatus.OK);
    }

    @PostMapping(value = "/png", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, produces = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Object> htmlToPng(@RequestParam MultipartFile multipartFile) {

        byte[] convert = htmlToImage.convert(multipartFile);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "output.png");

        return new ResponseEntity<>(convert, headers, HttpStatus.OK);
    }
}
