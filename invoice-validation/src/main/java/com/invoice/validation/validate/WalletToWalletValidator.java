package com.invoice.validation.validate;

import com.invoice.validation.dto.MessageDto;
import com.invoice.validation.dto.TransactionDto;
import org.springframework.stereotype.Component;

@Component
public class WalletToWalletValidator implements Validator {
  @Override
  public boolean shouldValidate(TransactionDto.TransactionType type) {
    return TransactionDto.TransactionType.WALLET_TO_WALLET.name().equals(type.name());
  }

  @Override
  public MessageDto apply(TransactionDto transactionDto) {
    return null;
  }
}
