package com.invoice.persistence.repository;

import com.invoice.persistence.model.Transaction;
import java.util.Collection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {
  Collection<Transaction> findByPayerId(Long payerId);
}
