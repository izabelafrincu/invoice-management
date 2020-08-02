package com.invoice.validation.validate.transaction;

import com.invoice.shared.dto.TransactionDto;
import com.invoice.shared.enums.TransactionMember;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import lombok.Getter;
import org.springframework.util.StringUtils;

@Getter
public class TransactionValidator {
  private Collection<String> errors = new ArrayList<>();

  public TransactionValidator validateDescription(String description) {
    if (StringUtils.isEmpty(description)) {
      errors.add("Transaction description is empty");
    }

    return this;
  }

  public TransactionValidator validateAmount(BigDecimal amount) {
    if (amount == null) {
      errors.add("Transaction amount is null");
    }

    return this;
  }

  public TransactionValidator validatePayer(TransactionMember transactionMember,
      TransactionDto.ClientDto clientDto) {
    errors.addAll(validateClient(transactionMember, clientDto));
    return this;
  }

  public TransactionValidator validatePayee(TransactionMember transactionMember,
      TransactionDto.ClientDto clientDto) {
    errors.addAll(validateClient(transactionMember, clientDto));
    return this;
  }

  private Collection<String> validateClient(TransactionMember transactionMember,
      TransactionDto.ClientDto clientDto) {

    Collection<String> result = ClientDetailsValidator.getInstance().validateClientDetails(clientDto);

    String name = clientDto.getName();
    String accountId = clientDto.getAccountId();
    switch (transactionMember) {
      case WALLET:
        result.addAll(WalletIdValidator.getInstance().validateField(name, accountId));
        break;
      default:
        result.addAll(IbanValidator.getInstance().validateField(name, accountId));
        break;
    }

    return result;
  }


}
