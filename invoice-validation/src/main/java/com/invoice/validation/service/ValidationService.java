package com.invoice.validation.service;

import com.invoice.shared.dto.TransactionDto;
import com.invoice.validation.dto.MessageDto;
import com.invoice.validation.persistence.client.InvoicePersistenceClient;
import com.invoice.validation.validate.Validator;
import feign.FeignException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Future;
import lombok.AllArgsConstructor;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ValidationService {
  private List<Validator> validators;
  private InvoicePersistenceClient invoicePersistenceClient;

  @Retryable(
      include = {FeignException.class},
      maxAttempts = 2,
      backoff = @Backoff(delay = 5000))
  public MessageDto validate(TransactionDto transactionDto) {
    Objects.requireNonNull(transactionDto.getType(), "Transaction type cannot be null");

    MessageDto messageDto = validators
        .stream()
        .filter(validator -> validator.shouldValidate(transactionDto.getType()))
        .findFirst()
        .map(validator -> validator.apply(transactionDto))
        .get();

    if (messageDto.isValid()) {
      sendData(transactionDto);
    }

    return messageDto;
  }

  @Async
  public Future<Long> sendData(TransactionDto transactionDto) {
    return new AsyncResult<>(invoicePersistenceClient.load(transactionDto));
  }

  @Recover
  public void recover(FeignException e, Long id) {
    System.out.println("test");
  }

}
