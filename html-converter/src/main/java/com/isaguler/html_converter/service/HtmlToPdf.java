package com.isaguler.html_converter.service;

import com.isaguler.html_converter.exception.HtmlReadException;
import com.isaguler.html_converter.exception.PdfFileNotExistException;
import com.isaguler.html_converter.exception.WrongFormatException;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class HtmlToPdf extends AbstractConverter<MultipartFile, byte[]> {

    private static final Logger log = LoggerFactory.getLogger(HtmlToPdf.class);

    String pdfPath = "pdf-path";

    @Override
    public byte[] convert(MultipartFile multipartFile) {
        String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        if (!Objects.equals(extension, "html")) {
            throw  new WrongFormatException("input file should be HTML");
        }

        File file;
        try {
            file = File.createTempFile("temp", null);
            Files.copy(multipartFile.getInputStream(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);

        } catch (Exception e) {
            throw new HtmlReadException(e.getMessage());
        }

        try (OutputStream os = new FileOutputStream(pdfPath)) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withFile(file);
            builder.toStream(os);
            builder.run();

            File pdfFile = new File(pdfPath);

            if (pdfFile.exists()) {
                return Files.readAllBytes(pdfFile.toPath());

            } else {
                throw new PdfFileNotExistException("pdf file does not exist");
            }

        } catch (Exception e) {
            log.info("Exception 2 : {}", e.getMessage());

            return new byte[0];
        }
    }
}
