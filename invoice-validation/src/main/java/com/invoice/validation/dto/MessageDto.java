package com.invoice.validation.dto;

import java.util.Collection;
import java.util.Collections;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MessageDto {
  private boolean isValid;
  private Collection<String> errors = Collections.emptyList();
}
