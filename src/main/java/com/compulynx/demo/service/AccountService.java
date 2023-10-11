package com.compulynx.demo.service;

import com.compulynx.demo.dao.response.ResponseMessage;
import com.compulynx.demo.entities.Account;

public interface AccountService {
   ResponseMessage createAccount(Account account);

   ResponseMessage updateAccount(Account account);

}
