package com.invoice.persistence.service;

import com.invoice.persistence.dto.TransactionDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PersistenceService {
  private UserService userService;
  private TransactionService transactionService;

  public Long loadData(TransactionDto request) {
    Long payerId = userService.saveUser(request.getType().getPayer(), request.getPayerDto());
    Long payeeId = userService.saveUser(request.getType().getPayee(), request.getPayeeDto());

    return transactionService.saveTransaction(request, payerId, payeeId);
  }

}