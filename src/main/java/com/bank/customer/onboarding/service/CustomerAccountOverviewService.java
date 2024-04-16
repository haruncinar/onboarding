package com.bank.customer.onboarding.service;

import com.bank.customer.onboarding.model.request.CustomerAccountOverviewRequest;
import com.bank.customer.onboarding.model.response.CustomerAccountOverviewResponse;

public interface CustomerAccountOverviewService {

    CustomerAccountOverviewResponse getAccountOverview(CustomerAccountOverviewRequest customerAccountOverviewRequest);
}
