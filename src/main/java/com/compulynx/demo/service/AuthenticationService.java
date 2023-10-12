package com.compulynx.demo.service;

import com.compulynx.demo.dao.request.SignUpRequest;
import com.compulynx.demo.dao.request.SigninRequest;
import com.compulynx.demo.dao.response.JwtAuthenticationResponse;


public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}
