package com.compulynx.demo.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.compulynx.demo.dao.request.SignUpRequest;
import com.compulynx.demo.dao.request.SigninRequest;
import com.compulynx.demo.dao.response.ResponseMessage;
import com.compulynx.demo.entities.Customer;
import com.compulynx.demo.entities.Role;
import com.compulynx.demo.entities.User;
import com.compulynx.demo.repository.UserRepository;
import com.compulynx.demo.service.CustomerService;
import com.compulynx.demo.service.JwtService;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AuthenticationServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AuthenticationServiceImplTest {
    @MockBean
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticationServiceImpl authenticationServiceImpl;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link AuthenticationServiceImpl#signup(SignUpRequest)}
     */
    @Test
    void testSignup() {
        User user = new User();
        user.setCustomerId("42");
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole(Role.USER);
        when(userRepository.save(Mockito.<User>any())).thenReturn(user);
        when(jwtService.generateToken(Mockito.<UserDetails>any())).thenReturn("ABC123");
        when(customerService.createCustomer(Mockito.<Customer>any()))
                .thenReturn(new ResponseMessage("Not all who wander are lost", 1));
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenReturn("secret");
        assertEquals("ABC123",
                authenticationServiceImpl.signup(new SignUpRequest("Name", "42", "jane.doe@example.org", "iloveyou"))
                        .getToken());
        verify(userRepository).save(Mockito.<User>any());
        verify(jwtService).generateToken(Mockito.<UserDetails>any());
        verify(customerService).createCustomer(Mockito.<Customer>any());
        verify(passwordEncoder, atLeast(1)).encode(Mockito.<CharSequence>any());
    }

    /**
     * Method under test: {@link AuthenticationServiceImpl#signup(SignUpRequest)}
     */
    @Test
    void testSignup2() {
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenThrow(new IllegalArgumentException("foo"));
        assertThrows(IllegalArgumentException.class,
                () -> authenticationServiceImpl.signup(new SignUpRequest("Name", "42", "jane.doe@example.org", "iloveyou")));
        verify(passwordEncoder).encode(Mockito.<CharSequence>any());
    }

    /**
     * Method under test: {@link AuthenticationServiceImpl#signup(SignUpRequest)}
     */
    @Test
    void testSignup3() {
        User user = new User();
        user.setCustomerId("42");
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole(Role.USER);
        when(userRepository.save(Mockito.<User>any())).thenReturn(user);
        when(jwtService.generateToken(Mockito.<UserDetails>any())).thenThrow(new IllegalArgumentException("foo"));
        when(customerService.createCustomer(Mockito.<Customer>any()))
                .thenReturn(new ResponseMessage("Not all who wander are lost", 1));
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenReturn("secret");
        assertThrows(IllegalArgumentException.class,
                () -> authenticationServiceImpl.signup(SignUpRequest.builder()
                        .customerId("42")
                        .email("jane.doe@example.org")
                        .name("Name")
                        .password("iloveyou")
                        .build()));
        verify(userRepository).save(Mockito.<User>any());
        verify(jwtService).generateToken(Mockito.<UserDetails>any());
        verify(customerService).createCustomer(Mockito.<Customer>any());
        verify(passwordEncoder, atLeast(1)).encode(Mockito.<CharSequence>any());
    }

    /**
     * Method under test: {@link AuthenticationServiceImpl#signin(SigninRequest)}
     */
    @Test
    void testSignin() throws AuthenticationException {
        User user = new User();
        user.setCustomerId("42");
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole(Role.USER);
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(ofResult);
        when(jwtService.generateToken(Mockito.<UserDetails>any())).thenReturn("ABC123");
        when(authenticationManager.authenticate(Mockito.<Authentication>any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        assertEquals("ABC123",
                authenticationServiceImpl.signin(new SigninRequest("jane.doe@example.org", "iloveyou")).getToken());
        verify(userRepository).findByEmail(Mockito.<String>any());
        verify(jwtService).generateToken(Mockito.<UserDetails>any());
        verify(authenticationManager).authenticate(Mockito.<Authentication>any());
    }

    /**
     * Method under test: {@link AuthenticationServiceImpl#signin(SigninRequest)}
     */
    @Test
    void testSignin2() throws AuthenticationException {
        when(authenticationManager.authenticate(Mockito.<Authentication>any()))
                .thenThrow(new IllegalArgumentException("foo"));
        assertThrows(IllegalArgumentException.class,
                () -> authenticationServiceImpl.signin(new SigninRequest("jane.doe@example.org", "iloveyou")));
        verify(authenticationManager).authenticate(Mockito.<Authentication>any());
    }

    /**
     * Method under test: {@link AuthenticationServiceImpl#signin(SigninRequest)}
     */
    @Test
    void testSignin3() throws AuthenticationException {
        Optional<User> emptyResult = Optional.empty();
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(emptyResult);
        when(authenticationManager.authenticate(Mockito.<Authentication>any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        assertThrows(IllegalArgumentException.class,
                () -> authenticationServiceImpl.signin(new SigninRequest("jane.doe@example.org", "iloveyou")));
        verify(userRepository).findByEmail(Mockito.<String>any());
        verify(authenticationManager).authenticate(Mockito.<Authentication>any());
    }
}

