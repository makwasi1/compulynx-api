package com.compulynx.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compulynx.demo.service.AccountService;



@RestController
@RequestMapping("/accounts")
public class AccountController {
    
    @Autowired
    private AccountService accountService;
    
    @RequestMapping
    public String hello() {
        return "Hello World";
    }

    
}
