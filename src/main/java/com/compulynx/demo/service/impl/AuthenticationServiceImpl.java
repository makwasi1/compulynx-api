package com.compulynx.demo.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.compulynx.demo.dao.request.SignUpRequest;
import com.compulynx.demo.dao.request.SigninRequest;
import com.compulynx.demo.dao.response.JwtAuthenticationResponse;
import com.compulynx.demo.entities.Customer;
import com.compulynx.demo.entities.Role;
import com.compulynx.demo.entities.User;
import com.compulynx.demo.repository.UserRepository;
import com.compulynx.demo.service.AuthenticationService;
import com.compulynx.demo.service.CustomerService;
import com.compulynx.demo.service.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final CustomerService customerService;
    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var user = User.builder().name(request.getName()).customerId(request.getCustomerId())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();
        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setCustomerId(request.getCustomerId());
        customer.setEmail(request.getEmail());
        customer.setPin(passwordEncoder.encode(request.getPassword()));        
        customerService.createCustomer(customer);
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}