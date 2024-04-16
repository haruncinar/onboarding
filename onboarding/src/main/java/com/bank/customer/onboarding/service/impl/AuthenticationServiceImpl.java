package com.bank.customer.onboarding.service.impl;


import com.bank.customer.onboarding.model.entity.*;
import com.bank.customer.onboarding.model.request.LogonRequest;
import com.bank.customer.onboarding.model.request.TokenRequest;
import com.bank.customer.onboarding.model.response.LogonResponse;
import com.bank.customer.onboarding.model.response.TokenResponse;
import com.bank.customer.onboarding.service.*;
import com.bank.customer.onboarding.util.Constants;
import com.bank.customer.onboarding.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final CustomerDetailService customerDetailService;

    @Override
    public TokenResponse getToken(TokenRequest tokenRequest) {
        Customer user = customerDetailService.findCustomerByNameAndPassword(tokenRequest.getUsername(), tokenRequest.getPassword());
        String token = JwtUtil.createToken(user);
        user.setToken(token);
        customerDetailService.save(user);
        return TokenResponse.builder().token(token).build();
    }

    @Override
    public LogonResponse login(LogonRequest logonRequest) {
        Customer customer = customerDetailService.findCustomerByNameAndPasswordAndToken(logonRequest.getUsername(), logonRequest.getPassword(), logonRequest.getToken());
        customer.setSessionValid(true);
        customerDetailService.save(customer);
        return LogonResponse.builder().success(Constants.LOGIN_SUCCESS_MESSAGE).build();
    }

    @Override
    public LogonResponse logout(LogonRequest logonRequest) {
        Customer customer = customerDetailService.findCustomerByNameAndPasswordAndToken(logonRequest.getUsername(), logonRequest.getPassword(), logonRequest.getToken());
        customer.setSessionValid(false);
        customerDetailService.save(customer);
        return LogonResponse.builder().success(Constants.LOGOUT_SUCCESS_MESSAGE).build();
    }




}
