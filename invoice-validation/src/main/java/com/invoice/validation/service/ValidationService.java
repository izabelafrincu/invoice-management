package com.invoice.validation.service;

import com.invoice.shared.dto.TransactionDto;
import com.invoice.validation.dto.MessageDto;
import com.invoice.validation.persistence.client.InvoicePersistenceClient;
import com.invoice.validation.validate.Validator;
import feign.FeignException;
import java.util.Collection;
import java.util.Collections;
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
  private Collection<Validator> validators;
  private InvoicePersistenceClient invoicePersistenceClient;

  @Retryable(value = {FeignException.class}, backoff = @Backoff(delay = 5000))
  public MessageDto validate(TransactionDto transactionDto) {
    if (transactionDto == null) {
      throw new IllegalArgumentException("Transaction cannot be null");
    }

    if (transactionDto.getType() == null) {
      throw new IllegalArgumentException("Transaction type cannot be null");
    }

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
  private Future<Long> sendData(TransactionDto transactionDto) {
    return new AsyncResult<>(invoicePersistenceClient.load(transactionDto));
  }

  @Recover
  public MessageDto recoverValidate(FeignException e, TransactionDto transactionDto) {
    return new MessageDto(true,
        Collections.singletonList("Could not save data! Please try again later. " + e.getMessage()));
  }

}
