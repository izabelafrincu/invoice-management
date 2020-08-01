package com.invoice.validation.validate.transaction;

import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class IbanValidator {
  private static final Pattern IBAN_PATTERN = Pattern.compile("^([A-Z]){2}\\d{2}([A-Z]){4}\\d{16}$");

  private IbanValidator() {
  }

  public static IbanValidator getInstance() {
    return new IbanValidator();
  }

  protected Collection<String> validateIBAN(String clientName, String IBAN) {
    if (IBAN == null) {
      return Collections.singletonList(String.format("Client=%s IBAN cannot be null", clientName));
    }
    if ("".equals(IBAN.trim())) {
      return Collections.singletonList(String.format("Client=%s IBAN is empty", clientName));
    }

    Matcher matcher = IBAN_PATTERN.matcher(IBAN);
    if (!matcher.matches()) {
      return Collections.singletonList(String.format("Client=%s has invalid IBAN=%s", clientName, IBAN));
    }

    return Collections.emptyList();
  }
}
