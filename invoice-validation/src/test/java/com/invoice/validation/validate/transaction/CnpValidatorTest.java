package com.invoice.validation.validate.transaction;

import java.util.Collection;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CnpValidatorTest {
  private static final String CLIENT_NAME = "test";

  private CnpValidator sut = CnpValidator.getInstance();

  @Test
  public void validateCNP_returnsErrorMessage_ifCNPIsNull() {
    Collection<String> result = sut.validateCNP(CLIENT_NAME, null);

    assertTrue(result.contains(String.format("Client=%s CNP cannot be null", CLIENT_NAME)));
  }

  @Test
  public void validateCNP_returnsErrorMessage_ifCNPIsEmpty() {
    Collection<String> result = sut.validateCNP(CLIENT_NAME, "    ");

    assertTrue(result.contains(String.format("Client=%s CNP is empty", CLIENT_NAME)));
  }

  @Test
  public void validateCNP_returnsErrorMessage_ifCNPHasLessThan13Digits() {
    String CNP = "33333";
    Collection<String> result = sut.validateCNP(CLIENT_NAME, CNP);

    assertTrue(result.contains(String.format("Client=%s CNP=%s has less than 13 digits", CLIENT_NAME, CNP)));
  }

  @Test
  public void validateCNP_returnsErrorMessage_ifCNPFirstDigitIsInvalid() {
    String CNP = "0234567890138";
    Collection<String> result = sut.validateCNP(CLIENT_NAME, CNP);

    assertTrue(result.contains(String.format("Client=%s has invalid CNP=%s", CLIENT_NAME, CNP)));
  }

  @Test
  public void validateCNP_returnsErrorMessage_ifCNPMonthIsInvalid() {
    String CNP = "2234567890138";
    Collection<String> result = sut.validateCNP(CLIENT_NAME, CNP);

    assertTrue(result.contains(String.format("Client=%s has invalid CNP=%s", CLIENT_NAME, CNP)));
  }

  @Test
  public void validateCNP_returnsErrorMessage_ifCNPDayIsInvalid() {
    String CNP = "1234567890138";
    Collection<String> result = sut.validateCNP(CLIENT_NAME, CNP);

    assertTrue(result.contains(String.format("Client=%s has invalid CNP=%s", CLIENT_NAME, CNP)));
  }

  @Test
  public void validateCNP_returnsErrorMessage_ifCNPCountyIsInvalid() {
    String CNP = "1423567890138";
    Collection<String> result = sut.validateCNP(CLIENT_NAME, CNP);

    assertTrue(result.contains(String.format("Client=%s has invalid CNP=%s", CLIENT_NAME, CNP)));
  }

  @Test
  public void validateCNP_returnsErrorMessage_ifCNPControlDigitIsInvalid() {
    String CNP = "1234567890138";
    Collection<String> result = sut.validateCNP(CLIENT_NAME, CNP);

    assertTrue(result.contains(String.format("Client=%s has invalid CNP=%s", CLIENT_NAME, CNP)));
  }

  @Test
  public void validateCNP_ReturnsNoError_ifCNPIsValid() {
    String CNP = "6200408086368";
    Collection<String> result = sut.validateCNP(CLIENT_NAME, CNP);

    assertTrue(result.isEmpty());
  }
}