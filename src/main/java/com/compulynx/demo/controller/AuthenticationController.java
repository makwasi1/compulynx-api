package com.compulynx.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compulynx.demo.dao.request.SigninRequest;
import com.compulynx.demo.dao.response.JwtAuthenticationResponse;
import com.compulynx.demo.dao.response.ResponseMessage;
import com.compulynx.demo.entities.Customer;
import com.compulynx.demo.service.AuthenticationService;
import com.compulynx.demo.service.CustomerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final CustomerService customerService;
    @PostMapping("/register")
    public ResponseMessage signup(@RequestBody Customer request) {
        try {
            // create user record and account
            customerService.createCustomer(request);
            // Return success response
            return new ResponseMessage("User and Account created successfully", 200);
        } catch (Exception e) {
            log.error("Exception: {}", e);
            // Return error response
            return new ResponseMessage("Failed to create User and Account", 500);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }
}
