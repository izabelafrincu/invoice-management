package com.invoice.validation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
  private @NonNull TransactionType type;
  private ClientDto payerDto;
  private @NonNull String description;
  private @NonNull BigDecimal amount;
  private ClientDto payeeDto;

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ClientDto {
    @JsonProperty("IBAN")
    private String IBAN;
    @JsonProperty("CNP")
    private String CNP;
    private String name;
  }

  public enum TransactionType {
    IBAN_TO_IBAN, IBAN_TO_WALLET, WALLET_TO_IBAN, WALLET_TO_WALLET;
  }
}
