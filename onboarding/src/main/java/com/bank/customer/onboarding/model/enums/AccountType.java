package com.bank.customer.onboarding.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public enum AccountType {

    Individual("Individual","Account for Individuals"),
    Family("Family","Joint account - Family Account"),
    Young("Young","Account for kids, below 18");

    private String name;
    private String description;
}
