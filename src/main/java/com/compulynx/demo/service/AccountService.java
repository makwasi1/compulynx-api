package com.compulynx.demo.service;

import java.util.List;

import com.compulynx.demo.dao.response.ResponseMessage;
import com.compulynx.demo.entities.Account;

public interface AccountService {
   ResponseMessage createAccount(Account account);

   ResponseMessage updateAccount(Account account);

   List<Account> getAllAccounts();

   double getBalanceByCustomerId(String customerId);

    ResponseMessage reduceBalanceByCustomerId(String customerId, int amount);
    ResponseMessage updateBalanceByCustomerId(String customerId, int amount);

}
