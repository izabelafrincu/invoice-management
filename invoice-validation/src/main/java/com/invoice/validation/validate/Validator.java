package com.invoice.validation.validate;

import com.invoice.validation.dto.MessageDto;
import com.invoice.validation.dto.TransactionDto;
import java.util.function.Function;

public interface Validator extends Function<TransactionDto, MessageDto> {
  boolean shouldValidate(TransactionDto.TransactionType type);


}
