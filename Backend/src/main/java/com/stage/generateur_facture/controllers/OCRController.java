package com.stage.generateur_facture.controllers;

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
public class OCRController {
    @PostMapping("/extract")
    public ResponseEntity<String> extractText(@RequestParam("file") MultipartFile file) {
        try {
            // Save the uploaded image to a temp file
            File tempFile = File.createTempFile("uploaded", file.getOriginalFilename());
            file.transferTo(tempFile);

            // OCR processing
            Tesseract tesseract = new Tesseract();
            tesseract.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata"); // path to tessdata
            tesseract.setLanguage("eng"); // or "fra", "ara", etc.

            String result = tesseract.doOCR(tempFile);
            return ResponseEntity.ok(result);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("OCR failed: " + e.getMessage());
        }
    }

}
