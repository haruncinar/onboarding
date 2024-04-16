package com.bank.customer.onboarding.model.exception;

import com.bank.customer.onboarding.util.Constants;

public class TooManyRequestException extends BaseOnboardingException {

    public TooManyRequestException()
    {
        super(Constants.TOO_MANY_REQUEST_FAILURE_MESSAGE);
    }
}
