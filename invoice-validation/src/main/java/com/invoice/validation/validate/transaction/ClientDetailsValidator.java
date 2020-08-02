package com.invoice.validation.validate.transaction;

import com.invoice.shared.dto.TransactionDto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import org.springframework.util.StringUtils;

final class ClientDetailsValidator {
  private ClientDetailsValidator() {
  }

  private static final ClientDetailsValidator INSTANCE = new ClientDetailsValidator();

  protected static ClientDetailsValidator getInstance() {
    return INSTANCE;
  }

  protected Collection<String> validateClientDetails(TransactionDto.ClientDto clientDto) {
    if (clientDto == null) {
      return Collections.singletonList("Client cannot be null");
    }

    String clientName = clientDto.getName();
    Collection<String> result = new ArrayList<>(CnpValidator.getInstance().validateField(clientName, clientDto.getCnp()));
    if (StringUtils.isEmpty(clientName)) {
      result.add("Client's name is empty");
    }

    return result;

  }
}
