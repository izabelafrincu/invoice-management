package com.invoice.persistence.service;

import com.invoice.persistence.model.Transaction;
import com.invoice.persistence.model.User;
import com.invoice.shared.enums.TransactionType;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@AllArgsConstructor
public class ReportService {
  private UserService userService;
  private TransactionService transactionService;
  private static final Font DOCUMENT_FONT = FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK);


  public ByteArrayOutputStream generateUserReport(String cnp) throws DocumentException {
    User payer = userService.findUserByCnp(cnp);

    Map<TransactionType, List<Transaction>> transactionByTypes =
        transactionService.findAllUserTransactions(payer.getId())
            .stream()
            .collect(Collectors.groupingBy(Transaction::getTransactionType));

    Document document = new Document();
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    PdfWriter.getInstance(document, bos);

    document.open();
    document.addTitle("Transactions Report");
    addPayerDetails(payer, document);
    addTransactionDetails(transactionByTypes, document);
    document.close();

    return bos;
  }

  private void addPayerDetails(User payer, Document document) throws DocumentException {
    addRow(String.format("Name: %s", payer.getName()), document);
    addRow(String.format("CNP: %s", payer.getCNP()), document);
    if (!StringUtils.isEmpty(payer.getIBAN())) {
      addRow(String.format("IBAN: %s", payer.getIBAN()), document);
    }
  }

  private void addTransactionDetails(Map<TransactionType, List<Transaction>> transactionByTypes, Document document)
      throws DocumentException {
    addRow("Transactions:", document);
    int transactionCount = 49;
    for (TransactionType transactionType : TransactionType.values()) {
      List<Transaction> transactions = transactionByTypes.getOrDefault(transactionType, Collections.emptyList());

      BigDecimal totalAmount =
          totalAmountTransferred(transactions.stream().map(Transaction::getAmount).collect(Collectors.toList()));
      addRow((char) transactionCount + ". " + transactionType.name() + " | " +
              (transactions.size() == 0 ? "no" : transactions.size()) + " transactions " +
              (totalAmount.equals(new BigDecimal(0)) ? "" : " | " + totalAmount + " RON"),
          document);
      transactionCount++;

      int transactionDetailCount = 97;
      for (Transaction t : transactions) {
        addRow(String.format("      %s. %s", (char) transactionDetailCount, t.getDescription()), document);
        transactionDetailCount++;
      }
    }
  }

  private BigDecimal totalAmountTransferred(Collection<BigDecimal> amounts) {
    return amounts.stream().reduce(new BigDecimal(0), BigDecimal::add);
  }

  private void addRow(String content, Document document) throws DocumentException {
    Paragraph row = new Paragraph(content, DOCUMENT_FONT);
    document.add(row);
  }
}
