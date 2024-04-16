package com.bank.customer.onboarding.repository;

import com.bank.customer.onboarding.model.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    Currency findCurrencyByCurrencyName(String currencyName);
}
