package com.invoice.validation.service;

import com.invoice.validation.dto.MessageDto;
import com.invoice.validation.dto.TransactionDto;
import com.invoice.validation.validate.TransactionValidator;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ValidationService {
  private List<TransactionValidator> validators;

  public MessageDto validate(TransactionDto transactionDto) {
    Objects.requireNonNull(transactionDto.getType(), "Transaction type cannot be null");

    MessageDto messageDto = validators
        .stream()
        .filter(validator -> validator.shouldValidate(transactionDto.getType()))
        .findFirst()
        .map(validator -> validator.apply(transactionDto))
        .get();

    if (messageDto.isValid()) {
      // call endpoint to save in db
    }

    return messageDto;
  }
}
