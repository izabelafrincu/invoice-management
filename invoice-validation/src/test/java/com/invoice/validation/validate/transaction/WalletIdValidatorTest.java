package com.invoice.validation.validate.transaction;

import java.util.Collection;
import java.util.UUID;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class WalletIdValidatorTest {
  private static final String CLIENT_NAME = "test";

  private WalletIdValidator sut = WalletIdValidator.getInstance();

  @Test
  public void validateWalletId_returnsErrorMessage_ifWalletIdIsNull() {
    Collection<String> result = sut.validateWalletId(CLIENT_NAME, null);

    assertTrue(result.contains(String.format("Client=%s walletId cannot be null", CLIENT_NAME)));
  }

  @Test
  public void validateWalletId_returnsErrorMessage_ifWalletIdIsEmpty() {
    Collection<String> result = sut.validateWalletId(CLIENT_NAME, "    ");

    assertTrue(result.contains(String.format("Client=%s walletId is empty", CLIENT_NAME)));
  }

  @Test
  public void validateWalletId_returnsErrorMessage_ifWalletIdIsNotUUID() {
    String walletId = "5678882-9h";
    Collection<String> result = sut.validateWalletId(CLIENT_NAME, walletId);

    assertTrue(result.contains(String.format("Client=%s has invalid walletId=%s", CLIENT_NAME, walletId)));
  }

  @Test
  public void validateWalletId_ReturnsNoError_ifWalletIdIsValid() {
    String walletId = UUID.randomUUID().toString();
    Collection<String> result = sut.validateWalletId(CLIENT_NAME, walletId);

    assertTrue(result.isEmpty());
  }
}