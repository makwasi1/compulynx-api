package com.compulynx.demo.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.compulynx.demo.dao.response.ResponseMessage;
import com.compulynx.demo.entities.Account;
import com.compulynx.demo.entities.Customer;
import com.compulynx.demo.entities.Transaction;
import com.compulynx.demo.repository.AccountRepository;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AccountServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AccountServiceImplTest {
    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private AccountServiceImpl accountServiceImpl;

    @MockBean
    private TransactionServiceImpl transactionServiceImpl;

    /**
     * Method under test: {@link AccountServiceImpl#createAccount(Account)}
     */
    @Test
    void testCreateAccount() {
        Customer customer = new Customer();
        customer.setCustomerId("42");
        customer.setDateCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        customer.setDateUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        customer.setEmail("jane.doe@example.org");
        customer.setId(1L);
        customer.setName("Name");
        customer.setPin("Pin");

        Account account = new Account();
        account.setAccountNumber("42");
        account.setBalance(1);
        account.setCustomer(customer);
        account.setDateCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        account.setDateUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        account.setId(1L);
        when(accountRepository.save(Mockito.<Account>any())).thenReturn(account);

        Customer customer2 = new Customer();
        customer2.setCustomerId("42");
        customer2.setDateCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        customer2.setDateUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        customer2.setEmail("jane.doe@example.org");
        customer2.setId(1L);
        customer2.setName("Name");
        customer2.setPin("Pin");

        Account account2 = new Account();
        account2.setAccountNumber("42");
        account2.setBalance(1);
        account2.setCustomer(customer2);
        account2.setDateCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        account2.setDateUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        account2.setId(1L);
        ResponseMessage actualCreateAccountResult = accountServiceImpl.createAccount(account2);
        assertEquals("Account created successfully", actualCreateAccountResult.getMessage());
        assertEquals(200, actualCreateAccountResult.getStatusCode());
        verify(accountRepository).save(Mockito.<Account>any());
    }

    /**
     * Method under test: {@link AccountServiceImpl#updateAccount(Account)}
     */
    @Test
    void testUpdateAccount() {
        Customer customer = new Customer();
        customer.setCustomerId("42");
        customer.setDateCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        customer.setDateUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        customer.setEmail("jane.doe@example.org");
        customer.setId(1L);
        customer.setName("Name");
        customer.setPin("Pin");

        Account account = new Account();
        account.setAccountNumber("42");
        account.setBalance(1);
        account.setCustomer(customer);
        account.setDateCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        account.setDateUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        account.setId(1L);
        when(accountRepository.save(Mockito.<Account>any())).thenReturn(account);

        Customer customer2 = new Customer();
        customer2.setCustomerId("42");
        customer2.setDateCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        customer2.setDateUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        customer2.setEmail("jane.doe@example.org");
        customer2.setId(1L);
        customer2.setName("Name");
        customer2.setPin("Pin");

        Account account2 = new Account();
        account2.setAccountNumber("42");
        account2.setBalance(1);
        account2.setCustomer(customer2);
        account2.setDateCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        account2.setDateUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        account2.setId(1L);
        ResponseMessage actualUpdateAccountResult = accountServiceImpl.updateAccount(account2);
        assertEquals("Account updated successfully", actualUpdateAccountResult.getMessage());
        assertEquals(200, actualUpdateAccountResult.getStatusCode());
        verify(accountRepository).save(Mockito.<Account>any());
    }

    /**
     * Method under test: {@link AccountServiceImpl#getAllAccounts()}
     */
    @Test
    void testGetAllAccounts() {
        ArrayList<Account> accountList = new ArrayList<>();
        when(accountRepository.findAll()).thenReturn(accountList);
        List<Account> actualAllAccounts = accountServiceImpl.getAllAccounts();
        assertSame(accountList, actualAllAccounts);
        assertTrue(actualAllAccounts.isEmpty());
        verify(accountRepository).findAll();
    }

    /**
     * Method under test: {@link AccountServiceImpl#getBalanceByCustomerId(String)}
     */
    @Test
    void testGetBalanceByCustomerId() {
        Customer customer = new Customer();
        customer.setCustomerId("42");
        customer.setDateCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        customer.setDateUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        customer.setEmail("jane.doe@example.org");
        customer.setId(1L);
        customer.setName("Name");
        customer.setPin("Pin");

        Account account = new Account();
        account.setAccountNumber("42");
        account.setBalance(1);
        account.setCustomer(customer);
        account.setDateCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        account.setDateUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        account.setId(1L);
        when(accountRepository.findByCustomerCustomerId(Mockito.<String>any())).thenReturn(account);
        assertEquals(1.0d, accountServiceImpl.getBalanceByCustomerId("42"));
        verify(accountRepository).findByCustomerCustomerId(Mockito.<String>any());
    }

    /**
     * Method under test: {@link AccountServiceImpl#updateBalanceByCustomerId(String, int)}
     */
    @Test
    void testUpdateBalanceByCustomerId() {
        Customer customer = new Customer();
        customer.setCustomerId("42");
        customer.setDateCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        customer.setDateUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        customer.setEmail("jane.doe@example.org");
        customer.setId(1L);
        customer.setName("Name");
        customer.setPin("Pin");

        Account account = new Account();
        account.setAccountNumber("42");
        account.setBalance(1);
        account.setCustomer(customer);
        account.setDateCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        account.setDateUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        account.setId(1L);

        Customer customer2 = new Customer();
        customer2.setCustomerId("42");
        customer2.setDateCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        customer2.setDateUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        customer2.setEmail("jane.doe@example.org");
        customer2.setId(1L);
        customer2.setName("Name");
        customer2.setPin("Pin");

        Account account2 = new Account();
        account2.setAccountNumber("42");
        account2.setBalance(1);
        account2.setCustomer(customer2);
        account2.setDateCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        account2.setDateUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        account2.setId(1L);
        when(accountRepository.save(Mockito.<Account>any())).thenReturn(account2);
        when(accountRepository.findByCustomerCustomerId(Mockito.<String>any())).thenReturn(account);
        doNothing().when(transactionServiceImpl).createTransaction(Mockito.<Transaction>any());
        ResponseMessage actualUpdateBalanceByCustomerIdResult = accountServiceImpl.updateBalanceByCustomerId("42", 10);
        assertEquals("Account updated successfully", actualUpdateBalanceByCustomerIdResult.getMessage());
        assertEquals(200, actualUpdateBalanceByCustomerIdResult.getStatusCode());
        verify(accountRepository).findByCustomerCustomerId(Mockito.<String>any());
        verify(accountRepository).save(Mockito.<Account>any());
        verify(transactionServiceImpl).createTransaction(Mockito.<Transaction>any());
    }

    /**
     * Method under test: {@link AccountServiceImpl#reduceBalanceByCustomerId(String, int)}
     */
    @Test
    void testReduceBalanceByCustomerId() {
        Customer customer = new Customer();
        customer.setCustomerId("42");
        customer.setDateCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        customer.setDateUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        customer.setEmail("jane.doe@example.org");
        customer.setId(1L);
        customer.setName("Name");
        customer.setPin("Pin");

        Account account = new Account();
        account.setAccountNumber("42");
        account.setBalance(1);
        account.setCustomer(customer);
        account.setDateCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        account.setDateUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        account.setId(1L);
        when(accountRepository.findByCustomerCustomerId(Mockito.<String>any())).thenReturn(account);
        ResponseMessage actualReduceBalanceByCustomerIdResult = accountServiceImpl.reduceBalanceByCustomerId("42", 10);
        assertEquals("Insufficient balance", actualReduceBalanceByCustomerIdResult.getMessage());
        assertEquals(400, actualReduceBalanceByCustomerIdResult.getStatusCode());
        verify(accountRepository).findByCustomerCustomerId(Mockito.<String>any());
    }

    /**
     * Method under test: {@link AccountServiceImpl#reduceBalanceByCustomerId(String, int)}
     */
    @Test
    void testReduceBalanceByCustomerId2() {
        Customer customer = new Customer();
        customer.setCustomerId("42");
        customer.setDateCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        customer.setDateUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        customer.setEmail("jane.doe@example.org");
        customer.setId(1L);
        customer.setName("Name");
        customer.setPin("Pin");

        Account account = new Account();
        account.setAccountNumber("42");
        account.setBalance(10);
        account.setCustomer(customer);
        account.setDateCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        account.setDateUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        account.setId(1L);

        Customer customer2 = new Customer();
        customer2.setCustomerId("42");
        customer2.setDateCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        customer2.setDateUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        customer2.setEmail("jane.doe@example.org");
        customer2.setId(1L);
        customer2.setName("Name");
        customer2.setPin("Pin");

        Account account2 = new Account();
        account2.setAccountNumber("42");
        account2.setBalance(1);
        account2.setCustomer(customer2);
        account2.setDateCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        account2.setDateUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        account2.setId(1L);
        when(accountRepository.save(Mockito.<Account>any())).thenReturn(account2);
        when(accountRepository.findByCustomerCustomerId(Mockito.<String>any())).thenReturn(account);
        doNothing().when(transactionServiceImpl).createTransaction(Mockito.<Transaction>any());
        ResponseMessage actualReduceBalanceByCustomerIdResult = accountServiceImpl.reduceBalanceByCustomerId("42", 10);
        assertEquals("Account updated successfully", actualReduceBalanceByCustomerIdResult.getMessage());
        assertEquals(200, actualReduceBalanceByCustomerIdResult.getStatusCode());
        verify(accountRepository).findByCustomerCustomerId(Mockito.<String>any());
        verify(accountRepository).save(Mockito.<Account>any());
        verify(transactionServiceImpl).createTransaction(Mockito.<Transaction>any());
    }
}

