package com.isaguler.exceltopdf.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.commons.math3.dfp.DfpField;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@Service
public class ConverterService {

    public byte[] convertExcelToPdf(MultipartFile multipartFile) throws IOException, DocumentException {
        File file = File.createTempFile("temp", null);
        Files.copy(multipartFile.getInputStream(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);

        FileInputStream fileInputStream = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file.toPath().toString()));
        document.open();

        XSSFSheet worksheet = workbook.getSheetAt(0);
        Row headerRow = worksheet.getRow(0);
        int numberOfColumns = headerRow.getPhysicalNumberOfCells();

        PdfPTable pdfPTable = new PdfPTable(numberOfColumns);

        for (Row row : worksheet) {
            if (row.getRowNum() == 0) {
                continue;
            }
            for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
                Cell cell = row.getCell(i);
                String cellValue = switch (cell.getCellType()) {
                    case STRING -> cell.getStringCellValue();
                    case NUMERIC -> String.valueOf(BigDecimal.valueOf(cell.getNumericCellValue()));
                    default -> "";
                };
                PdfPCell cellPdf = new PdfPCell(new Phrase(cellValue));
                pdfPTable.addCell(cellPdf);
            }
        }

        document.add(pdfPTable);
        document.close();
        workbook.close();

        return Files.readAllBytes(file.toPath());
    }
}
