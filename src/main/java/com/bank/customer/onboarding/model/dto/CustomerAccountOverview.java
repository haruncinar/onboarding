package com.bank.customer.onboarding.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class CustomerAccountOverview {

    private Long accountNumber;

    private String accountType;

    private BigDecimal accountBalance;

    private String accountCurrency;
}
