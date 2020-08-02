package com.invoice.validation.validate.transaction;

import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class IbanValidator implements FieldValidator {
  private static final Pattern IBAN_PATTERN = Pattern.compile("^([A-Z]){2}\\d{2}([A-Z]){4}\\d{16}$");

  private IbanValidator() {
  }

  private static final IbanValidator INSTANCE = new IbanValidator();

  protected static IbanValidator getInstance() {
    return INSTANCE;
  }

  public Collection<String> validateField(String clientName, String iban) {
    if (iban == null) {
      return Collections.singletonList(String.format("Client=%s IBAN cannot be null", clientName));
    }
    if ("".equals(iban.trim())) {
      return Collections.singletonList(String.format("Client=%s IBAN is empty", clientName));
    }

    Matcher matcher = IBAN_PATTERN.matcher(iban);
    if (!matcher.matches()) {
      return Collections.singletonList(String.format("Client=%s has invalid IBAN=%s", clientName, iban));
    }

    return Collections.emptyList();
  }
}
