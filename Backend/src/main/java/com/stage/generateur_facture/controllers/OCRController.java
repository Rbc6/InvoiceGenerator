package com.stage.generateur_facture.controllers;

import com.stage.generateur_facture.entities.dto.InvoiceRequest;
import com.stage.generateur_facture.services.Parser.InvoiceParserService;
import lombok.RequiredArgsConstructor;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
@RestController
@RequestMapping("/api/ocr")
@RequiredArgsConstructor
public class OCRController {

    private final InvoiceParserService parserService;

    @PostMapping("/extract")
    public ResponseEntity<InvoiceRequest> extractText(@RequestParam("file") MultipartFile file) {
        try {
            File tempFile = File.createTempFile("uploaded", file.getOriginalFilename());
            file.transferTo(tempFile);

            Tesseract tesseract = new Tesseract();
            tesseract.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata");
            tesseract.setLanguage("eng");

            String ocrText = tesseract.doOCR(tempFile);

            InvoiceRequest invoice = parserService.parseInvoiceText(ocrText);
            return ResponseEntity.ok(invoice);

        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}

