package com.isaguler.html_converter.service;

import com.isaguler.html_converter.exception.HtmlReadException;
import com.isaguler.html_converter.exception.PdfFileNotExistException;
import com.isaguler.html_converter.exception.WrongFormatException;
import com.openhtmltopdf.java2d.api.BufferedImagePageProcessor;
import com.openhtmltopdf.java2d.api.Java2DRendererBuilder;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class HtmlToImage  extends AbstractConverter<MultipartFile, byte[]> {

    String imagePath = "image-path";

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

        Java2DRendererBuilder builder = new Java2DRendererBuilder();
        builder.withFile(file);
        builder.useFastMode();

        BufferedImagePageProcessor bufferedImagePageProcessor =
                new BufferedImagePageProcessor(BufferedImage.TYPE_INT_RGB, 1.0);

        builder.toSinglePage(bufferedImagePageProcessor);

        try {
            builder.runFirstPage();
        } catch (Exception e) {
            throw new RuntimeException("Java2DRendererBuilder exception ", e);
        }

        try {
            ImageIO.write(bufferedImagePageProcessor.getPageImages().getFirst(), "png", new FileOutputStream(imagePath));

            File imageFile = new File(imagePath);

            if (imageFile.exists()) {
                return Files.readAllBytes(imageFile.toPath());

            } else {
                throw new PdfFileNotExistException("pdf file does not exist");
            }

        } catch (Exception e) {
            throw new RuntimeException("image write exception ", e);
        }

    }
}
