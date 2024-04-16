package com.bank.customer.onboarding.service;

import com.bank.customer.onboarding.model.request.RegistrationRequest;
import com.bank.customer.onboarding.model.response.RegistrationResponse;

public interface RegistrationService {

    RegistrationResponse register(RegistrationRequest registrationRequest);
}
