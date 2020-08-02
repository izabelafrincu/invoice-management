package com.invoice.validation.validate.transaction;

import java.util.Collection;
import java.util.Collections;

final class CnpValidator implements FieldValidator {
  private static final String CODE = "279146358279";

  private CnpValidator() {
  }

  private static final CnpValidator INSTANCE = new CnpValidator();

  protected static CnpValidator getInstance() {
    return INSTANCE;
  }


  public Collection<String> validateField(String clientName, String cnp) {
    if (cnp == null) {
      return Collections.singletonList(String.format("Client=%s CNP cannot be null", clientName));
    }

    if ("".equals(cnp.trim())) {
      return Collections.singletonList(String.format("Client=%s CNP is empty", clientName));
    }

    if (cnp.length() < 13) {
      return Collections.singletonList(String.format("Client=%s CNP=%s has less than 13 digits", clientName, cnp));
    }

    if (!checkCnpDigits(cnp)) {
      return Collections.singletonList(String.format("Client=%s has invalid CNP=%s", clientName, cnp));
    }

    return Collections.emptyList();
  }

  private boolean checkCnpDigits(String cnp) {
    if (Character.getNumericValue(cnp.charAt(0)) < 1) {
      return false;
    }

    int month = Integer.valueOf(cnp.substring(3, 5));
    if (month < 1 || month > 12) {
      return false;
    }
    int day = Integer.valueOf(cnp.substring(5, 7));
    if (day < 1 || day > 31) {
      return false;
    }

    int county = Integer.valueOf(cnp.substring(7, 9));
    if ((county < 1 || county > 52) || (county == 47 || county == 48 || county == 49 || county == 50)) {
      return false;
    }

    return isLastDigitValid(cnp);
  }

  private boolean isLastDigitValid(String cnp) {
    int sum = 0;
    for (int i = 0; i < CODE.length(); i++) {
      int codeDigit = Character.getNumericValue(CODE.charAt(i));
      int CNPDigit = Character.getNumericValue(cnp.charAt(i));

      sum += (CNPDigit * codeDigit);
    }

    int rest = sum % 11;
    if (rest == 10) {
      rest = 1;
    }
    return Character.getNumericValue(cnp.charAt(12)) == rest;
  }
}
