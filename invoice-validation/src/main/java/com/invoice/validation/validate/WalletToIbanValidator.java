package com.invoice.validation.validate;

import com.invoice.validation.dto.MessageDto;
import com.invoice.validation.dto.TransactionDto;
import org.springframework.stereotype.Component;

@Component
public class WalletToIbanValidator implements Validator {
  @Override
  public boolean shouldValidate(TransactionDto.TransactionType type) {
    return TransactionDto.TransactionType.WALLET_TO_IBAN.name().equals(type.name());
  }

  @Override
  public MessageDto apply(TransactionDto transactionDto) {
    return null;
  }
}
