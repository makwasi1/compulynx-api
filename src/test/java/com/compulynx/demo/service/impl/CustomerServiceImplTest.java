package com.compulynx.demo.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.compulynx.demo.dao.response.ResponseMessage;
import com.compulynx.demo.entities.Account;
import com.compulynx.demo.entities.Customer;
import com.compulynx.demo.repository.CustomerRepository;
import com.compulynx.demo.service.AccountService;

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

@ContextConfiguration(classes = {CustomerServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CustomerServiceImplTest {
    @MockBean
    private AccountService accountService;

    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    /**
     * Method under test: {@link CustomerServiceImpl#createCustomer(Customer)}
     */
    @Test
    void testCreateCustomer() {
        when(accountService.createAccount(Mockito.<Account>any()))
                .thenReturn(new ResponseMessage("Not all who wander are lost", 1));

        Customer customer = new Customer();
        customer.setCustomerId("42");
        customer.setDateCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        customer.setDateUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        customer.setEmail("jane.doe@example.org");
        customer.setId(1L);
        customer.setName("Name");
        customer.setPin("Pin");
        when(customerRepository.save(Mockito.<Customer>any())).thenReturn(customer);

        Customer customer2 = new Customer();
        customer2.setCustomerId("42");
        customer2.setDateCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        customer2.setDateUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        customer2.setEmail("jane.doe@example.org");
        customer2.setId(1L);
        customer2.setName("Name");
        customer2.setPin("Pin");
        ResponseMessage actualCreateCustomerResult = customerServiceImpl.createCustomer(customer2);
        assertEquals("User and Account created successfully", actualCreateCustomerResult.getMessage());
        assertEquals(200, actualCreateCustomerResult.getStatusCode());
        verify(accountService).createAccount(Mockito.<Account>any());
        verify(customerRepository).save(Mockito.<Customer>any());
    }

    /**
     * Method under test: {@link CustomerServiceImpl#getAllCustomers()}
     */
    @Test
    void testGetAllCustomers() {
        ArrayList<Customer> customerList = new ArrayList<>();
        when(customerRepository.findAll()).thenReturn(customerList);
        List<Customer> actualAllCustomers = customerServiceImpl.getAllCustomers();
        assertSame(customerList, actualAllCustomers);
        assertTrue(actualAllCustomers.isEmpty());
        verify(customerRepository).findAll();
    }

    /**
     * Method under test: {@link CustomerServiceImpl#getCustomer(String, String)}
     */
    @Test
    void testGetCustomer() {
        Customer customer = new Customer();
        customer.setCustomerId("42");
        customer.setDateCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        customer.setDateUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        customer.setEmail("jane.doe@example.org");
        customer.setId(1L);
        customer.setName("Name");
        customer.setPin("Pin");
        when(customerRepository.findByCustomerIdAndPin(Mockito.<String>any(), Mockito.<String>any()))
                .thenReturn(customer);
        assertSame(customer, customerServiceImpl.getCustomer("Customer ID", "Pin"));
        verify(customerRepository).findByCustomerIdAndPin(Mockito.<String>any(), Mockito.<String>any());
    }

    /**
     * Method under test: {@link CustomerServiceImpl#userDetailsService()}
     */
    @Test
    void testUserDetailsService() {
        // TODO: Complete this test.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     1.this$0

        customerServiceImpl.userDetailsService();
    }
}

