package com.invoice.validation.validate.transaction;

import com.invoice.validation.dto.TransactionDto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import org.springframework.util.StringUtils;

class ClientDetailsValidator {
  private ClientDetailsValidator() {
  }

  public static ClientDetailsValidator getInstance() {
    return new ClientDetailsValidator();
  }

  protected Collection<String> validateClientDetails(TransactionDto.ClientDto clientDto) {
    if (clientDto == null) {
      return Collections.singletonList("Client cannot be null");
    }

    String clientName = clientDto.getName();
    Collection<String> result = new ArrayList<>(CnpValidator.getInstance().validateCNP(clientName, clientDto.getCNP()));
    if (StringUtils.isEmpty(clientName)) {
      result.add("Client's name is empty");
    }

    return result;

  }
}
