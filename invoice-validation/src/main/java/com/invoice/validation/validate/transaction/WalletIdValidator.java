package com.invoice.validation.validate.transaction;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

class WalletIdValidator {
  private WalletIdValidator() {
  }

  public static WalletIdValidator getInstance() {
    return new WalletIdValidator();
  }

  protected Collection<String> validateWalletId(String clientName, String walletId) {
    if (walletId == null){
      return Collections.singletonList(String.format("Client=%s walletId cannot be null", clientName));
    }

    if ("".equals(walletId.trim())) {
      return Collections.singletonList(String.format("Client=%s walletId is empty", clientName));
    }

    try {
      UUID.fromString(walletId);
    } catch (IllegalArgumentException e) {
      return Collections.singletonList(String.format("Client=%s has invalid walletId=%s", clientName, walletId));
    }

    return Collections.emptyList();
  }
}
