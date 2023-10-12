package com.compulynx.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compulynx.demo.service.AccountService;



@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
    
    @Autowired
    private AccountService accountService;
    
    // get balance for a given customer
    @GetMapping("/balance/{customerId}")
    public double getBalance(@PathVariable String customerId) {
        return accountService.getBalanceByCustomerId(customerId);
    }

}
