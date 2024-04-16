package com.bank.customer.onboarding.service;

import com.bank.customer.onboarding.model.request.LogonRequest;
import com.bank.customer.onboarding.model.request.TokenRequest;
import com.bank.customer.onboarding.model.response.LogonResponse;
import com.bank.customer.onboarding.model.response.TokenResponse;

public interface AuthenticationService {

    TokenResponse getToken(TokenRequest tokenRequest);

    LogonResponse login(LogonRequest logonRequest);

    LogonResponse logout(LogonRequest logonRequest);

}
