package com.compulynx.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.compulynx.demo.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByCustomerId(Long customerId);

    Account findByCustomerCustomerId(String customerId);
}
