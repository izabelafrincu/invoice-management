package com.invoice.persistence.service;

import com.itextpdf.text.DocumentException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;

public class ReportServiceIT extends BaseIT {
  private static final String CNP = "7081217355211";

  @Autowired
  private ReportService sut;

  @Test
  public void generateUserReport_ReturnsPdf_IfCNPExists() throws DocumentException, IOException {
    ByteArrayOutputStream result = sut.generateUserReport(CNP);

    Files.write(Paths.get("userReport.pdf"), result.toByteArray());
    assertNotNull(result);
  }
}