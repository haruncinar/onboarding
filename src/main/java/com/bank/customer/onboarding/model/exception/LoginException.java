package com.bank.customer.onboarding.model.exception;

import com.bank.customer.onboarding.util.Constants;

public class LoginException extends BaseOnboardingException {

    public LoginException()
    {
        super(Constants.LOGIN_FAILURE_MESSAGE);
    }
}
