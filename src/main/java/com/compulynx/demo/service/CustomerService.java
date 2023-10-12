package com.compulynx.demo.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.compulynx.demo.dao.response.ResponseMessage;
import com.compulynx.demo.entities.Customer;

public interface CustomerService {

    ResponseMessage createCustomer(Customer customer);

    List<Customer> getAllCustomers();

    Customer getCustomer(String customerID, String pin);

    UserDetailsService userDetailsService();

}
