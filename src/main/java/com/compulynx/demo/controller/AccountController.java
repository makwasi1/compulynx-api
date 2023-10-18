package com.compulynx.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compulynx.demo.dao.request.AccountRequest;
import com.compulynx.demo.dao.response.ResponseMessage;
import com.compulynx.demo.service.AccountService;



@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

   
    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    
    @Autowired
    private AccountService accountService;
    
    // get balance for a given customer
    @GetMapping("/balance/{customerId}")
    public double getBalance(@PathVariable String customerId) {
        log.info("Received a request for MyController");
        return accountService.getBalanceByCustomerId(customerId);
    }

    // get account by customer id and update the balance
    @PostMapping("/deposit/{customerId}")
    public ResponseMessage deposit(@PathVariable String customerId, @RequestBody AccountRequest request) {
        log.info(request.toString());
        return accountService.updateBalanceByCustomerId(customerId, request.getBalance());
    }

    // get account by customer id and update the balance
    @PostMapping("/withdraw/{customerId}")
    public ResponseMessage withdraw(@RequestBody AccountRequest request, @PathVariable String customerId) {
        return accountService.reduceBalanceByCustomerId(customerId, request.getBalance());
    }

}
