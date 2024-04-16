package com.bank.customer.onboarding.model.exception;

import com.bank.customer.onboarding.util.Constants;

public class UserAgeNotAllowedException extends BaseOnboardingException {

    public UserAgeNotAllowedException()
    {
        super(Constants.USER_AGE_NOT_ALLOWED_FAILURE_MESSAGE);
    }
}
