package com.compulynx.demo.service.impl;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compulynx.demo.dao.response.ResponseMessage;
import com.compulynx.demo.entities.Account;
import com.compulynx.demo.entities.Customer;
import com.compulynx.demo.repository.CustomerRepository;
import com.compulynx.demo.service.AccountService;
import com.compulynx.demo.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountService accountService;

    // create user and account
    public ResponseMessage createCustomer(Customer customer) {
        try {
            // create user record and account record
            customer.setPin(generateRandomNumber());
            Customer savedCustomer = customerRepository.save(customer);

            // Create the account record
            Account account = new Account();
            account.setAccountNumber(generateRandomNumber());
            account.setBalance(0);
            account.setCustomer(savedCustomer);
            account.setDateCreated(new Date());
            account.setDateUpdated(new Date());
            accountService.createAccount(account);
            // Return success response
            return new ResponseMessage("User and Account created successfully", 200);
                } catch (Exception e) {
            log.error("Exception: {}", e);
            // Return error response
            return new ResponseMessage("Failed to create User and Account", 500);
                }
        }
       
    private String generateRandomNumber() {
        Random random = new Random();
        StringBuilder accountNumber = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            accountNumber.append(random.nextInt(10));
        }
        return accountNumber.toString();
    }

}
