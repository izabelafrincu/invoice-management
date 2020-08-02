package com.invoice.persistence.controller;

import com.invoice.persistence.service.ReportService;
import com.itextpdf.text.DocumentException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invoice-persistence/report/users/")
@AllArgsConstructor
public class ReportController {
  private ReportService reportService;

  @GetMapping(value = "/{userId}")
  public ResponseEntity<InputStreamResource> generateReport(@PathVariable String userId) throws DocumentException {

    ByteArrayOutputStream bos = reportService.generateUserReport(userId);
    ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());

    HttpHeaders headers = new HttpHeaders();
    headers.setContentDispositionFormData("attachment", "userReport.pdf");

    return ResponseEntity
        .ok()
        .cacheControl(CacheControl.noCache())
        .contentType(MediaType.APPLICATION_PDF)
        .headers(headers)
        .body(new InputStreamResource(bis));
  }
}
