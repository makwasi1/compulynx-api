package com.compulynx.demo.service;

import com.compulynx.demo.dao.response.ResponseMessage;
import com.compulynx.demo.entities.Customer;

public interface CustomerService {

    ResponseMessage createCustomer(Customer customer);

}
