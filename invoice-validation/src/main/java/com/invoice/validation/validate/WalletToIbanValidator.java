package com.invoice.validation.validate;

import com.invoice.validation.dto.MessageDto;
import com.invoice.validation.dto.TransactionDto;
import com.invoice.validation.enums.TransactionType;
import java.util.Collection;
import org.springframework.stereotype.Component;

@Component
public class WalletToIbanValidator implements TransactionValidator {

  @Override
  public TransactionType getTransactionType() {
    return TransactionType.WALLET_TO_IBAN;
  }

  @Override
  public boolean shouldValidate(TransactionType type) {
    return getTransactionType().name().equals(type.name());
  }

  @Override
  public MessageDto apply(TransactionDto transactionDto) {
    TransactionType transactionType = getTransactionType();
    Collection<String> errorMessages = new TransactionFieldsValidator()
        .validateDescription(transactionDto.getDescription())
        .validateAmount(transactionDto.getAmount())
        .validatePayer(transactionType.getPayer(), transactionDto.getPayerDto())
        .validatePayee(transactionType.getPayee(), transactionDto.getPayeeDto())
        .getErrors();

    return new MessageDto(errorMessages.isEmpty(), errorMessages);
  }
}
