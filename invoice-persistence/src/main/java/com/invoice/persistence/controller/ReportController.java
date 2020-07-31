package com.invoice.persistence.controller;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invoice-persistence/report")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ReportController {

  @GetMapping
  public ResponseEntity<InputStreamResource> generateReport() throws DocumentException {
    Document document = new Document();
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PdfWriter.getInstance(document, out);

    document.open();
    Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
    Chunk chunk = new Chunk("Hello World", font);

    document.add(chunk);
    document.close();

    ByteArrayInputStream bis = new ByteArrayInputStream(out.toByteArray());

    HttpHeaders headers = new HttpHeaders();
    headers.setContentDispositionFormData("attachment", "iTextHelloWorld.pdf");
    return ResponseEntity
        .ok()
        .cacheControl(CacheControl.noCache())
        .contentType(MediaType.APPLICATION_PDF)
        .headers(headers)
        .body(new InputStreamResource(bis));
  }
}
