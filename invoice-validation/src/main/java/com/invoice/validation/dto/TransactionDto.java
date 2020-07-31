package com.invoice.validation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.invoice.validation.enums.TransactionType;
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
  private @NonNull ClientDto payerDto;
  private String description;
  private BigDecimal amount;
  private @NonNull ClientDto payeeDto;

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ClientDto {
    @JsonProperty("IBAN")
    private String IBAN;
    @JsonProperty("CNP")
    private String CNP;
    private Long walletId;
    private String name;
  }

}
