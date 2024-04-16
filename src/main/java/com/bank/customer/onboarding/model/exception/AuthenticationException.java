package com.bank.customer.onboarding.model.exception;

import com.bank.customer.onboarding.util.Constants;

public class AuthenticationException extends BaseOnboardingException {

    public AuthenticationException()
    {
        super(Constants.AUTHENTICATION_FAILURE_MESSAGE);
    }
}
