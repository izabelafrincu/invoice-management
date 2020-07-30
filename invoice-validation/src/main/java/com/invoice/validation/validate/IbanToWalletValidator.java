package com.invoice.validation.validate;

import com.invoice.validation.dto.MessageDto;
import com.invoice.validation.dto.TransactionDto;
import java.util.ArrayList;
import java.util.Collection;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class IbanToWalletValidator implements Validator {
  private ClientValidator clientValidator;

  @Override
  public boolean shouldValidate(TransactionDto.TransactionType type) {
    return TransactionDto.TransactionType.IBAN_TO_WALLET.name().equals(type.name());
  }

  @Override
  public MessageDto apply(TransactionDto transactionDto) {
    final Collection<String> errorMessages = new ArrayList<>();
    errorMessages.addAll(clientValidator.validateClient(transactionDto.getPayerDto()));

    return new MessageDto(errorMessages.isEmpty(), errorMessages);
  }
}
