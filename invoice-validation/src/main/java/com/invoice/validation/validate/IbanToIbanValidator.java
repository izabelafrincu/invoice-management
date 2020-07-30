package com.invoice.validation.validate;

import com.invoice.validation.dto.MessageDto;
import com.invoice.validation.dto.TransactionDto;
import java.util.ArrayList;
import java.util.Collection;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class IbanToIbanValidator implements Validator {
  private ClientValidator clientValidator;

  @Override
  public boolean shouldValidate(TransactionDto.TransactionType type) {
    return TransactionDto.TransactionType.IBAN_TO_IBAN.name().equals(type.name());
  }

  @Override
  public MessageDto apply(TransactionDto transactionDto) {
    final Collection<String> errorMessages = new ArrayList<>();
    errorMessages.addAll(clientValidator.validateClient(transactionDto.getPayerDto()));
    errorMessages.addAll(clientValidator.validateClient(transactionDto.getPayeeDto()));

    return new MessageDto(errorMessages.isEmpty(), errorMessages);
  }
}
