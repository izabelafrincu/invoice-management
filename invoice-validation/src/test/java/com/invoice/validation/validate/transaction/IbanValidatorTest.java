package com.invoice.validation.validate.transaction;

import java.util.Collection;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class IbanValidatorTest {
  private static final String CLIENT_NAME = "test";

  private IbanValidator sut = IbanValidator.getInstance();

  @Test
  public void validateIBAN_returnsErrorMessage_ifIBANIsNull() {
    Collection<String> result = sut.validateIBAN(CLIENT_NAME, null);

    assertTrue(result.contains(String.format("Client=%s IBAN cannot be null", CLIENT_NAME)));
  }

  @Test
  public void validateIBAN_returnsErrorMessage_ifIBANIsEmpty() {
    Collection<String> result = sut.validateIBAN(CLIENT_NAME, "    ");

    assertTrue(result.contains(String.format("Client=%s IBAN is empty", CLIENT_NAME)));
  }

  @Test
  public void validateIBAN_returnsErrorMessage_ifIBANIsNotUUID() {
    String IBAN = "RO12PORL41917181168941";
    Collection<String> result = sut.validateIBAN(CLIENT_NAME, IBAN);

    assertTrue(result.contains(String.format("Client=%s has invalid IBAN=%s", CLIENT_NAME, IBAN)));
  }

  @Test
  public void validateIBAN_ReturnsNoError_ifIBANIsValid() {
    String IBAN = "RO12PORL4191718116894198";
    Collection<String> result = sut.validateIBAN(CLIENT_NAME, IBAN);

    assertTrue(result.isEmpty());
  }
}