package com.bank.customer.onboarding.model.exception;

public class BaseOnboardingException extends RuntimeException{

    protected BaseOnboardingException(String message)
    {
        super(message);
    }
}
