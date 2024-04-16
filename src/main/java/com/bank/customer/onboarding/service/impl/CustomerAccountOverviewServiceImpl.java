package com.bank.customer.onboarding.service.impl;

import com.bank.customer.onboarding.model.entity.Customer;
import com.bank.customer.onboarding.model.dto.CustomerAccountOverview;
import com.bank.customer.onboarding.model.exception.AuthenticationException;
import com.bank.customer.onboarding.model.request.CustomerAccountOverviewRequest;
import com.bank.customer.onboarding.model.response.CustomerAccountOverviewResponse;
import com.bank.customer.onboarding.repository.CustomerRepository;
import com.bank.customer.onboarding.service.CustomerAccountOverviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerAccountOverviewServiceImpl implements CustomerAccountOverviewService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerAccountOverviewResponse getAccountOverview(CustomerAccountOverviewRequest customerAccountOverviewRequest) {
        Optional<Customer> customerOptional = customerRepository.getCustomerWithAccountDetails(customerAccountOverviewRequest.getCustomerId(),
                customerAccountOverviewRequest.getToken());
        if(customerOptional.isEmpty()) {
            throw new AuthenticationException();
        }

        Customer customer = customerOptional.get();

        return CustomerAccountOverviewResponse.builder().customerAccountOverviewList(
                customer.getAccounts().stream().map(account ->
                        CustomerAccountOverview.builder()
                                .accountNumber(account.getId())
                                .accountType(account.getAccountType().name())
                                .accountCurrency(account.getCurrency().getCurrencyName())
                                .accountBalance(account.getAccountBalance()).build()
                ).toList()
        ).build();
    }
}
