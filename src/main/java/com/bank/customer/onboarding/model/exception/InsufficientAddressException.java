package com.bank.customer.onboarding.model.exception;

import com.bank.customer.onboarding.util.Constants;

public class InsufficientAddressException extends BaseOnboardingException {

    public InsufficientAddressException()
    {
        super(Constants.INSUFFICIENT_ADDRESS_INFO_MESSAGE);
    }
}
