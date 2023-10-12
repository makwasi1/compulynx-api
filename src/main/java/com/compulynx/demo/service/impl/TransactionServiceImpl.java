package com.compulynx.demo.service.impl;

import com.compulynx.demo.entities.Transaction;
import com.compulynx.demo.repository.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compulynx.demo.service.TransactionService;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private  TransactionsRepository transactionsRepository;

    public List<Transaction> getAllTransactions() {
        return transactionsRepository.findAll();
    }

    //create a transaction for each deposit or withdrawal
    public void createTransaction(Transaction transaction) {
        transactionsRepository.save(transaction);
    }

    //find by customer id
    public List<Transaction> findByCustomerId(String customerId, int limit) {
       //limit the return to only the last 10
        List <Transaction> transactions = transactionsRepository.findByCustomerId(customerId);
        if (transactions.size() > limit) {
            return transactions.subList(transactions.size() - 10, transactions.size());
        }
        return transactions;
    }
}
