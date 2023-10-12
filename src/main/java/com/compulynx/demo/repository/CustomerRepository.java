package com.compulynx.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.compulynx.demo.entities.Customer;



public interface CustomerRepository extends JpaRepository<Customer, Long> {
      Optional<Customer> findByCustomerId(String customerID);

      Customer findByCustomerIdAndPin(String customerID, String pin);

      Customer findById(long id);



      
}
