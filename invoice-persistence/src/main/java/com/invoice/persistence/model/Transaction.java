package com.invoice.persistence.model;

import com.invoice.shared.enums.TransactionType;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transactions")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
public class Transaction {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String description;
  private BigDecimal amount;
  private TransactionType transactionType;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "payer_id", nullable = false)
  private User payer;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "payee_id", nullable = false)
  private User payee;
}
