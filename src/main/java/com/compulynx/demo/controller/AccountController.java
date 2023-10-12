package com.compulynx.demo.controller;

import com.compulynx.demo.dao.request.AccountRequest;
import com.compulynx.demo.dao.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    // get account by customer id and update the balance
    @PostMapping("/deposit/{customerId}")
    public ResponseMessage deposit(@PathVariable String customerId, @RequestBody AccountRequest request) {
        return accountService.updateBalanceByCustomerId(customerId, request.getBalance());
    }

    // get account by customer id and update the balance
    @PostMapping("/withdraw/{customerId}")
    public ResponseMessage withdraw(@RequestBody AccountRequest request, @PathVariable String customerId) {
        return accountService.reduceBalanceByCustomerId(customerId, request.getBalance());
    }

}
