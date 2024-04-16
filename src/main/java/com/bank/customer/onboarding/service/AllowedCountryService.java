package com.bank.customer.onboarding.service;

import com.bank.customer.onboarding.model.entity.AllowedCountry;
import com.bank.customer.onboarding.model.request.AllowedCountryRequest;
import com.bank.customer.onboarding.model.response.AllowedCountryResponse;

import java.util.List;

public interface AllowedCountryService {
    List<AllowedCountry> getCountries();
    String getCountryCodeByCountryName(String countryName);
    AllowedCountryResponse save(AllowedCountryRequest allowedCountryRequest);
}
