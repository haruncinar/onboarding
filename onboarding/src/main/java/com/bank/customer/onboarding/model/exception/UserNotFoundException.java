package com.bank.customer.onboarding.model.exception;

import com.bank.customer.onboarding.util.Constants;

public class UserNotFoundException extends BaseOnboardingException {

    public UserNotFoundException()
    {
        super(Constants.USER_NOT_FOUND_MESSAGE);
    }
}
