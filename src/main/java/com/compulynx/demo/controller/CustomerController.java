package com.compulynx.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compulynx.demo.dao.response.ResponseMessage;
import com.compulynx.demo.entities.Customer;
import com.compulynx.demo.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/v1/customers")
@Slf4j
public class CustomerController {
    
    @Autowired
    private CustomerService customerService;

    /**
     * @param customer
     * @return
     */
    @PostMapping("/register")
    public ResponseMessage createCustomer(@RequestBody Customer customer) {
        try {
            // create user record and account
            customerService.createCustomer(customer);
            // Return success response
            return new ResponseMessage("User and Account created successfully", 200);
        } catch (Exception e) {
            log.error("Exception: {}", e);
            // Return error response
            return new ResponseMessage("Failed to create User and Account", 500);
        }
    }


    //get all users from the database
    @GetMapping("/users")
    public List<Customer> getAllUsers() {
        return customerService.getAllCustomers();
    }
    
}
