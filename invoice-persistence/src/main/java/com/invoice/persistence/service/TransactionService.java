package com.invoice.persistence.service;

import com.invoice.persistence.dto.TransactionDto;
import com.invoice.persistence.model.Transaction;
import com.invoice.persistence.model.User;
import com.invoice.persistence.repository.TransactionRepository;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
class TransactionService {
  private TransactionRepository transactionRepository;

  protected Long saveTransaction(TransactionDto request, Long payerId, Long payeeId) {
    Transaction transaction = Transaction.builder()
        .transactionType(request.getType().name())
        .description(request.getDescription())
        .amount(request.getAmount())
        .payer(createUser(payerId))
        .payee(createUser(payeeId))
        .build();

    transactionRepository.save(transaction);

    return transaction.getId();
  }

  private User createUser(Long userId) {
    User user = new User();
    user.setId(userId);

    return user;
  }

  @Transactional(readOnly = true)
  protected Collection<Transaction> findAllUserTransactions(Long payerId) {
    return transactionRepository.findByPayerId(payerId);
  }
}