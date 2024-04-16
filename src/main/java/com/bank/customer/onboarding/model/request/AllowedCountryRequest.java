package com.bank.customer.onboarding.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AllowedCountryRequest {
    private String countryName;
    private String countryCode;
}
