package com.invoice.validation.validate.transaction;

import java.util.Collection;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CnpValidatorTest {
  private static final String CLIENT_NAME = "test";

  private CnpValidator sut = CnpValidator.getInstance();

  @Test
  public void validateField_ReturnsErrorMessage_IfCNPIsNull() {
    Collection<String> result = sut.validateField(CLIENT_NAME, null);

    assertTrue(result.contains(String.format("Client=%s CNP cannot be null", CLIENT_NAME)));
  }

  @Test
  public void validateField_ReturnsErrorMessage_IfCNPIsEmpty() {
    Collection<String> result = sut.validateField(CLIENT_NAME, "    ");

    assertTrue(result.contains(String.format("Client=%s CNP is empty", CLIENT_NAME)));
  }

  @Test
  public void validateField_ReturnsErrorMessage_IfCNPHasLessThan13Digits() {
    String CNP = "33333";
    Collection<String> result = sut.validateField(CLIENT_NAME, CNP);

    assertTrue(result.contains(String.format("Client=%s CNP=%s has less than 13 digits", CLIENT_NAME, CNP)));
  }

  @Test
  public void validateField_ReturnsErrorMessage_IfCNPFirstDigitIsInvalid() {
    String CNP = "0234567890138";
    Collection<String> result = sut.validateField(CLIENT_NAME, CNP);

    assertTrue(result.contains(String.format("Client=%s has invalid CNP=%s", CLIENT_NAME, CNP)));
  }

  @Test
  public void validateField_ReturnsErrorMessage_IfCNPMonthIsInvalid() {
    String CNP = "2234567890138";
    Collection<String> result = sut.validateField(CLIENT_NAME, CNP);

    assertTrue(result.contains(String.format("Client=%s has invalid CNP=%s", CLIENT_NAME, CNP)));
  }

  @Test
  public void validateField_ReturnsErrorMessage_IfCNPDayIsInvalid() {
    String CNP = "1234567890138";
    Collection<String> result = sut.validateField(CLIENT_NAME, CNP);

    assertTrue(result.contains(String.format("Client=%s has invalid CNP=%s", CLIENT_NAME, CNP)));
  }

  @Test
  public void validateField_ReturnsErrorMessage_IfCNPCountyIsInvalid() {
    String CNP = "1423567890138";
    Collection<String> result = sut.validateField(CLIENT_NAME, CNP);

    assertTrue(result.contains(String.format("Client=%s has invalid CNP=%s", CLIENT_NAME, CNP)));
  }

  @Test
  public void validateField_ReturnsErrorMessage_IfCNPControlDigitIsInvalid() {
    String CNP = "1234567890138";
    Collection<String> result = sut.validateField(CLIENT_NAME, CNP);

    assertTrue(result.contains(String.format("Client=%s has invalid CNP=%s", CLIENT_NAME, CNP)));
  }

  @Test
  public void validateField_ReturnsNoError_IfCNPIsValid() {
    String CNP = "2990817305562";
    Collection<String> result = sut.validateField(CLIENT_NAME, CNP);

    assertTrue(result.isEmpty());
  }
}