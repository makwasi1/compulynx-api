package com.compulynx.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compulynx.demo.dao.response.ResponseMessage;
import com.compulynx.demo.entities.Account;
import com.compulynx.demo.repository.AccountRepository;
import com.compulynx.demo.service.AccountService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public ResponseMessage createAccount(Account account) {
        try {
            // Create the account record
            accountRepository.save(account);
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
            return new ResponseMessage("Account updated successfully", 200);
        }
        return new ResponseMessage("Account not found", 404);
    }

}
