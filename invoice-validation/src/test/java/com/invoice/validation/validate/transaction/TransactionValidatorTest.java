package com.invoice.validation.validate.transaction;

import com.invoice.shared.dto.TransactionDto;
import com.invoice.shared.enums.TransactionMember;
import java.math.BigDecimal;
import java.util.UUID;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TransactionValidatorTest {
  private static final String INVALID_IBAN = "RO22TEST878989890909874";
  private static final String VALID_IBAN = "RO85RZBR1224457571422363";
  private static final String INVALID_WALLET_ID = "778990-92992";
  private static final String VALID_WALLET_ID = UUID.randomUUID().toString();

  private static final String CNP = "6200408086368";
  private static final String NAME = "ION Ionescu";

  private TransactionValidator sut = new TransactionValidator();

  @Test
  public void validateDescription_ReturnsErrorMessage_IfDescriptionIsNull() {
    TransactionValidator result = sut.validateDescription(null);

    assertTrue(result.getErrors().contains("Transaction description is empty"));
  }

  @Test
  public void validateDescription_ReturnsErrorMessage_IfDescriptionIsEmpty() {
    TransactionValidator result = sut.validateDescription("");

    assertTrue(result.getErrors().contains("Transaction description is empty"));
  }

  @Test
  public void validateDescription_ReturnsNoError_IfDescriptionIsNotEmpty() {
    TransactionValidator result = sut.validateDescription("description");

    assertTrue(result.getErrors().isEmpty());
  }

  @Test
  public void validateAmount_ReturnsErrorMessage_IfAmountIsNull() {
    TransactionValidator result = sut.validateAmount(null);

    assertTrue(result.getErrors().contains("Transaction amount is null"));
  }

  @Test
  public void validateAmount_ReturnsNoError_IfAmountIsValid() {
    TransactionValidator result = sut.validateAmount(new BigDecimal(129999.777));

    assertTrue(result.getErrors().isEmpty());
  }

  @Test
  public void validatePayer_ReturnsErrorMessage_IfIBANIsInvalid() {
    TransactionDto.ClientDto payer = mockClientDto(INVALID_IBAN);
    TransactionValidator result = sut.validatePayer(TransactionMember.IBAN, payer);

    assertTrue(result.getErrors().contains(String.format("Client=%s has invalid IBAN=%s", NAME, INVALID_IBAN)));
  }

  @Test
  public void validatePayer_ReturnsNoError_IfIBANIsValid() {
    TransactionDto.ClientDto payer = mockClientDto(VALID_IBAN);
    TransactionValidator result = sut.validatePayer(TransactionMember.IBAN, payer);

    assertTrue(result.getErrors().isEmpty());
  }

  @Test
  public void validatePayee_ReturnsErrorMessage_IfWalletIdIsInvalid() {
    TransactionDto.ClientDto payee = mockClientDto(INVALID_WALLET_ID);
    TransactionValidator result = sut.validatePayee(TransactionMember.WALLET, payee);

    assertTrue(result.getErrors().contains(String.format("Client=%s has invalid walletId=%s", NAME,
        INVALID_WALLET_ID)));
  }

  @Test
  public void validatePayee_ReturnsNoError_IfWalletIdIsValid() {
    TransactionDto.ClientDto payee = mockClientDto(VALID_WALLET_ID);
    TransactionValidator result = sut.validatePayee(TransactionMember.WALLET, payee);

    assertTrue(result.getErrors().isEmpty());
  }

  private TransactionDto.ClientDto mockClientDto(String accountId) {
    return new TransactionDto.ClientDto(CNP, accountId, NAME);
  }

}