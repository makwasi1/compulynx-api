package com.compulynx.demo.repository;

import com.compulynx.demo.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionsRepository extends JpaRepository<Transaction, Long> {
  //find by customer id
    List<Transaction> findByCustomerId(String customerId);
}
