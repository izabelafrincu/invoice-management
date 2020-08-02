package com.invoice.validation.validate.transaction;

import java.util.Collection;

public interface FieldValidator {
  Collection<String> validateField(String clientName, String fieldValue);
}
