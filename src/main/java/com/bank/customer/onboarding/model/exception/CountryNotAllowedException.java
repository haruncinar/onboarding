package com.bank.customer.onboarding.model.exception;

import com.bank.customer.onboarding.util.Constants;

public class CountryNotAllowedException extends BaseOnboardingException {

    public CountryNotAllowedException()
    {
        super(Constants.COUNTRY_NOT_ALLOWED_MESSAGE);
    }
}
