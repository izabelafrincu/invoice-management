package com.invoice.persistence.dto;

import com.invoice.persistence.enums.TransactionType;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
  private TransactionType type;
  private ClientDto payerDto;
  private String description;
  private BigDecimal amount;
  private ClientDto payeeDto;

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ClientDto {
    private String cnp;
    private String accountId;
    private String name;
  }

}
