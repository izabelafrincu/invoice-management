package com.invoice.validation.validate;

import com.invoice.validation.dto.MessageDto;
import com.invoice.validation.dto.TransactionDto;
import com.invoice.validation.enums.TransactionType;
import java.util.function.Function;

public interface TransactionValidator extends Function<TransactionDto, MessageDto> {
  TransactionType getTransactionType();

  boolean shouldValidate(TransactionType type);
}
