package com.bank.customer.onboarding.model.exception;

import com.bank.customer.onboarding.util.Constants;

public class UserExistException extends BaseOnboardingException {

    public UserExistException()
    {
        super(Constants.USER_EXIST_FAILURE_MESSAGE);
    }
}
