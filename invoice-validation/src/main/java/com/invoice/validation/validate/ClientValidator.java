package com.invoice.validation.validate;

import com.invoice.validation.dto.TransactionDto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class ClientValidator {

  protected enum FieldType {
    IBAN, CNP
  }

  protected static final String IBAN_FIELD = "IBAN";
  protected static final String CNP_FIELD = "CNP";
  protected static final Pattern CNP_PATTERN = Pattern.compile("^[1-9]\\d{12}$");
  protected static final Pattern IBAN_PATTERN = Pattern.compile("^([A-Z]){2}\\d{2}([A-Z]){4}([0-9a-zA-Z]){16}$");

  protected Collection<String> validateClient(TransactionDto.ClientDto clientDto) {
    final Collection<String> result = new ArrayList<>();

    String clientName = clientDto.getName();
    if (!validateField(FieldType.CNP, clientDto.getCNP())) {
      result.add(String.format("Client=%s has invalid CNP=%s", clientName, clientDto.getCNP()));
    }
    if (!validateField(FieldType.IBAN, clientDto.getIBAN())) {
      result.add(String.format("Client=%s has invalid IBAN=%s", clientName, clientDto.getIBAN()));
    }

    if (StringUtils.isEmpty(clientName)) {
      result.add("Client's name is empty");
    }

    return result;

  }

  private boolean validateField(FieldType type, String value) {
    Matcher matcher;
    switch (type.name()) {
      case IBAN_FIELD:
        matcher = IBAN_PATTERN.matcher(value);
        break;
      default:
        matcher = CNP_PATTERN.matcher(value);
        break;
    }
    return matcher.matches();
  }
}
