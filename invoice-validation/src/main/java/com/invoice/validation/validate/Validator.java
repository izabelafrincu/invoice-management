package com.invoice.validation.validate;

import com.invoice.shared.dto.TransactionDto;
import com.invoice.shared.enums.TransactionType;
import com.invoice.validation.dto.MessageDto;
import java.util.function.Function;

public interface Validator extends Function<TransactionDto, MessageDto> {
  TransactionType getTransactionType();

  boolean shouldValidate(TransactionType type);
}
