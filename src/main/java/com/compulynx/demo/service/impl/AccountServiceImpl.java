package com.compulynx.demo.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compulynx.demo.controller.AccountController;
import com.compulynx.demo.dao.response.ResponseMessage;
import com.compulynx.demo.entities.Account;
import com.compulynx.demo.entities.Transaction;
import com.compulynx.demo.repository.AccountRepository;
import com.compulynx.demo.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger log = LoggerFactory.getLogger(AccountController.class);


    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionServiceImpl transactionServiceImpl;

    public ResponseMessage createAccount(Account account) {
        try {
            // Create the account record
            accountRepository.save(account);
            log.info("Account created successfully");
            return new ResponseMessage("Account created successfully", 200);
        } catch (Exception e) {
            log.error("Exception: {}", e);
            return new ResponseMessage("Failed to create Account", 500);
        }
    }

    public ResponseMessage updateAccount(Account account) {
        try {
            // Update the account record
            accountRepository.save(account);
            return new ResponseMessage("Account updated successfully", 200);
        } catch (Exception e) {
            log.error("Exception: {}", e);
            return new ResponseMessage("Failed to update Account", 500);
        }
    }

    // get all account
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }


    //get customer balance
    public double getBalanceByCustomerId(String customerId) {
        Account account = accountRepository.findByCustomerCustomerId(customerId);
        if (account != null) {
            return account.getBalance();
        }
        return 0;
        
    }

    //get account by customer id and update the balance
    public ResponseMessage updateBalanceByCustomerId(String customerId, int amount) {
        Account account = accountRepository.findByCustomerCustomerId(customerId);
        if (account != null) {
            account.setBalance(account.getBalance() + amount);
            accountRepository.save(account);
            //create a transaction for each deposit or withdrawal
            Transaction transaction = new Transaction();
            transaction.setAccount(account);
            transaction.setAmount(amount);
            transaction.setCustomerId(customerId);
            transaction.setTransactionType("deposit");
            transactionServiceImpl.createTransaction(transaction);

            return new ResponseMessage("Account updated successfully", 200);
        }
        return new ResponseMessage("Account not found", 404);
    }

    //reduce customer balance
    public ResponseMessage reduceBalanceByCustomerId(String customerId, int amount) {
        Account account = accountRepository.findByCustomerCustomerId(customerId);
        if (account != null) {
            if (amount > account.getBalance()) {
                return new ResponseMessage("Insufficient balance", 400);
            }
            account.setBalance(account.getBalance() - amount);
            accountRepository.save(account);
            //create a transaction for each withdrawal
            Transaction transaction = new Transaction();
            transaction.setAccount(account);
            transaction.setAmount(amount);
            transaction.setCustomerId(customerId);
            transaction.setTransactionType("withdrawal");
            transactionServiceImpl.createTransaction(transaction);
            return new ResponseMessage("Account updated successfully", 200);
        }
        return new ResponseMessage("Account not found", 404);
    }

}
