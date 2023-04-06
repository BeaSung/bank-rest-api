package com.bea.bank.repository;

import com.bea.bank.domain.TransactionHistory;
import org.springframework.data.repository.ListCrudRepository;

public interface TransactionHistoryRepository extends ListCrudRepository<TransactionHistory, Long> {
}
