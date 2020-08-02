package com.invoice.persistence.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.invoice.persistence.model.Transaction;
import com.invoice.persistence.repository.TransactionRepository;
import com.invoice.persistence.repository.UserRepository;
import com.invoice.shared.dto.TransactionDto;
import java.io.IOException;
import java.io.InputStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;

import static org.junit.Assert.assertEquals;

public class PersistenceServiceIT extends BaseIT {
  @Autowired
  private ResourceLoader resourceLoader;
  @Autowired
  private PersistenceService sut;

  @Autowired
  private TransactionRepository transactionRepository;
  @Autowired
  private UserRepository userRepository;

  private TransactionDto transactionDto;
  private Long transactionId;
  private Long payerId;
  private Long payeeId;

  @Before
  public void setUp() throws IOException {
    InputStream transactionInStream = resourceLoader.getResource("classpath:/valid-transaction.json").getInputStream();

    ObjectMapper objectMapper = new ObjectMapper();
    transactionDto = objectMapper.readValue(transactionInStream, TransactionDto.class);
  }

  @After
  public void cleanUp() {
    transactionRepository.deleteById(transactionId);
    userRepository.deleteById(payerId);
    userRepository.deleteById(payeeId);
  }

  @Test
  public void loadData_ReturnsTransactionId_IfInputIsValid() {
    Long result = sut.loadData(transactionDto);

    Transaction transaction = transactionRepository.findById(result).get();
    transactionId = transaction.getId();
    payerId = transaction.getPayer().getId();
    payeeId = transaction.getPayee().getId();

    assertEquals(10L, (long) transaction.getId());
    assertEquals(transactionDto.getAmount(), transaction.getAmount());
    assertEquals(transactionDto.getDescription(), transaction.getDescription());
    assertEquals(transactionDto.getType(), transaction.getTransactionType());
    assertEquals(transactionDto.getPayerDto().getCnp(), transaction.getPayer().getCNP());
    assertEquals(transactionDto.getPayeeDto().getCnp(), transaction.getPayee().getCNP());
  }

}