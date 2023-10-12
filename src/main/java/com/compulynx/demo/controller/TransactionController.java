package com.compulynx.demo.controller;

import com.compulynx.demo.entities.Transaction;
import com.compulynx.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    //get paged transactions from the database

    @GetMapping("/all")
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    //find last 10 transactions by customer id
    @GetMapping("/mini-statement/{customerId}/{limit}")
    public List<Transaction> findByCustomerId(@PathVariable String customerId, @PathVariable int limit) {
        return transactionService.findByCustomerId(customerId, limit);
    }

    
}
