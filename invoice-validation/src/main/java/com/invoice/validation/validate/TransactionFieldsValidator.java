package com.invoice.validation.validate;

import com.invoice.validation.dto.TransactionDto;
import com.invoice.validation.enums.TransactionMember;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Getter;
import org.springframework.util.StringUtils;

@Getter
public class TransactionFieldsValidator {
  private Collection<String> errors = new ArrayList<>();

  protected TransactionFieldsValidator validateDescription(String description) {
    if (StringUtils.isEmpty(description)) {
      errors.add("Transaction description is empty");
    }
    return this;
  }

  protected TransactionFieldsValidator validateAmount(BigDecimal amount) {
    if (amount == null) {
      errors.add("Transaction amount is null");
    }
    return this;
  }

  protected TransactionFieldsValidator validatePayer(TransactionMember transactionMember,
      TransactionDto.ClientDto clientDto) {

    errors.addAll(validateClient(transactionMember, clientDto));
    return this;
  }

  protected TransactionFieldsValidator validatePayee(TransactionMember transactionMember,
      TransactionDto.ClientDto clientDto) {

    errors.addAll(validateClient(transactionMember, clientDto));
    return this;
  }


  private Collection<String> validateClient(TransactionMember transactionMember,
      TransactionDto.ClientDto clientDto) {
    switch (transactionMember) {
      case WALLET:
        return validateWalletClient(clientDto);
      default:
        return validateIBANClient(clientDto);
    }
  }

  private enum FieldType {
    IBAN, CNP
  }

  private Collection<String> validateIBANClient(TransactionDto.ClientDto clientDto) {
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

  private static final Pattern CNP_PATTERN = Pattern.compile("^[1-9]\\d{12}$");
  private static final Pattern IBAN_PATTERN = Pattern.compile("^([A-Z]){2}\\d{2}([A-Z]){4}([0-9a-zA-Z]){16}$");

  private boolean validateField(FieldType type, String value) {
    Matcher matcher;
    switch (type) {
      case IBAN:
        matcher = IBAN_PATTERN.matcher(value);
        break;
      default:
        matcher = CNP_PATTERN.matcher(value);
        break;
    }
    return matcher.matches();
  }

  private Collection<String> validateWalletClient(TransactionDto.ClientDto clientDto) {

    final Collection<String> result = new ArrayList<>();
    if (clientDto.getWalletId() == null) {
      result.add("Client's walletId is null");
    }

    return result;
  }

}
