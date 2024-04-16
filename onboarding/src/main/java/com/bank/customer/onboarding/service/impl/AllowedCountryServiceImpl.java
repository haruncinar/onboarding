package com.bank.customer.onboarding.service.impl;

import com.bank.customer.onboarding.model.entity.AllowedCountry;
import com.bank.customer.onboarding.model.request.AllowedCountryRequest;
import com.bank.customer.onboarding.model.response.AllowedCountryResponse;
import com.bank.customer.onboarding.repository.AllowedCountryRepository;
import com.bank.customer.onboarding.service.AllowedCountryService;
import com.bank.customer.onboarding.util.Constants;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AllowedCountryServiceImpl implements AllowedCountryService {
    private final AllowedCountryRepository allowedCountryRepository;


    @Override
    public List<AllowedCountry> getCountries() {
        return allowedCountryRepository.findAll();
    }

    @Override
    public String getCountryCodeByCountryName(String countryName) {
        AllowedCountry allowedCountry = allowedCountryRepository.findAllowedCountryByCountryName(countryName);
        return allowedCountry.getCountryCode();
    }

    @Override
    @Transactional
    public AllowedCountryResponse save(AllowedCountryRequest allowedCountryRequest) {
        AllowedCountry allowedCountry = new AllowedCountry();
        allowedCountry.setCountryCode(allowedCountry.getCountryCode());
        allowedCountry.setCountryName(allowedCountryRequest.getCountryName());
        allowedCountryRepository.save(allowedCountry);
        return AllowedCountryResponse.builder().message(Constants.COUNTRY_SUCCESSFUL_MESSAGE).build();
    }
}
