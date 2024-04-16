package com.bank.customer.onboarding.service.impl;

import com.bank.customer.onboarding.model.entity.Currency;
import com.bank.customer.onboarding.repository.CurrencyRepository;
import com.bank.customer.onboarding.service.CurrencyService;
import com.bank.customer.onboarding.util.Constants;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private CurrencyRepository currencyRepository;

    @Override
    public Currency getCurrencyByName(String currencyName) {
        return currencyRepository.findCurrencyByCurrencyName(Constants.EURO);
    }
}
