package com.bank.customer.onboarding.repository;

import com.bank.customer.onboarding.model.entity.AllowedCountry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllowedCountryRepository extends JpaRepository<AllowedCountry, Long> {
    AllowedCountry findAllowedCountryByCountryName(String name);
}
