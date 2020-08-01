package com.invoice.persistence.service;

import com.invoice.persistence.model.Transaction;
import com.invoice.persistence.model.User;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.util.Collection;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReportService {
  private UserService userService;
  private TransactionService transactionService;

  public ByteArrayOutputStream generateUserReport(String CNP) throws DocumentException {
    User payer = userService.findUserByCnp(CNP);

    Collection<Transaction> transactions = transactionService.findAllUserTransactions(payer.getId());

    transactions.forEach(tran -> System.out.println(tran));

    Document document = new Document();
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    PdfWriter.getInstance(document, out);

    document.open();
    Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
    Chunk chunk = new Chunk("Hello World", font);

    document.add(chunk);
    document.close();

    return out;
  }
}
