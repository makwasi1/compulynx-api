package com.compulynx.demo.service;

import com.compulynx.demo.entities.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> getAllTransactions();

    //create a transaction for each deposit or withdrawal
     void createTransaction(Transaction transaction);

        //find by customer id
    List<Transaction> findByCustomerId(String customerId, int limit);

}
