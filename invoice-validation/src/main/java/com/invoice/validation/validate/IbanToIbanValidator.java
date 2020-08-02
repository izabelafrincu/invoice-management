package com.invoice.validation.validate;

import com.invoice.shared.dto.TransactionDto;
import com.invoice.shared.enums.TransactionType;
import com.invoice.validation.dto.MessageDto;
import com.invoice.validation.validate.transaction.TransactionValidator;
import java.util.Collection;
import org.springframework.stereotype.Component;

@Component
public class IbanToIbanValidator implements Validator {

  @Override
  public TransactionType getTransactionType() {
    return TransactionType.IBAN_TO_IBAN;
  }

  @Override
  public boolean shouldValidate(TransactionType type) {
    return getTransactionType().name().equals(type.name());
  }

  @Override
  public MessageDto apply(TransactionDto transactionDto) {
    TransactionType transactionType = getTransactionType();
    Collection<String> errorMessages = new TransactionValidator()
        .validateDescription(transactionDto.getDescription())
        .validateAmount(transactionDto.getAmount())
        .validatePayer(transactionType.getPayer(), transactionDto.getPayerDto())
        .validatePayee(transactionType.getPayee(), transactionDto.getPayeeDto())
        .getErrors();

    return new MessageDto(errorMessages.isEmpty(), errorMessages);
  }
}
