package com.invoice.validation.validate.transaction;

import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class CnpValidator {
  private static final Pattern CNP_PATTERN = Pattern.compile("\\d{13}");
  private static final String CODE = "279146358279";

  private CnpValidator() {
  }

  public static CnpValidator getInstance() {
    return new CnpValidator();
  }


  protected Collection<String> validateCNP(String clientName, String CNP) {
    if (CNP == null) {
      return Collections.singletonList(String.format("Client=%s CNP cannot be null", clientName));
    }

    if ("".equals(CNP.trim())) {
      return Collections.singletonList(String.format("Client=%s CNP is empty", clientName));
    }

    Matcher matcher = CNP_PATTERN.matcher(CNP);
    if (!matcher.matches()) {
      return Collections.singletonList(String.format("Client=%s CNP=%s has less than 13 digits", clientName, CNP));
    }

    if (!checkCnpDigits(CNP)) {
      return Collections.singletonList(String.format("Client=%s has invalid CNP=%s", clientName, CNP));
    }

    return Collections.emptyList();
  }

  private boolean checkCnpDigits(String CNP) {
    if (Character.getNumericValue(CNP.charAt(0)) < 1) {
      return false;
    }

    int month = Integer.valueOf(CNP.substring(3, 5));
    if (month < 1 || month > 12) {
      return false;
    }
    int day = Integer.valueOf(CNP.substring(5, 7));
    if (day < 1 || day > 31) {
      return false;
    }

    int county = Integer.valueOf(CNP.substring(7, 9));
    if ((county < 1 || county > 52) || (county == 47 || county == 48 || county == 49 || county == 50)) {
      return false;
    }

    return isLastDigitValid(CNP);
  }

  private boolean isLastDigitValid(String CNP) {
    int sum = 0;
    for (int i = 0; i < CODE.length(); i++) {
      int codeDigit = Character.getNumericValue(CODE.charAt(i));
      int CNPDigit = Character.getNumericValue(CNP.charAt(i));

      sum += (CNPDigit * codeDigit);
    }

    int rest = sum % 11;
    if (rest == 10) {
      rest = 1;
    }
    return Character.getNumericValue(CNP.charAt(12)) == rest;
  }
}
