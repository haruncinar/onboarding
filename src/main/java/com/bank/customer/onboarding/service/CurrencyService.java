package com.bank.customer.onboarding.service;

import com.bank.customer.onboarding.model.entity.Currency;

public interface CurrencyService {
    Currency getCurrencyByName(String currencyName);
}
