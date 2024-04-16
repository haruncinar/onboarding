package com.bank.customer.onboarding.service;


import com.bank.customer.onboarding.model.entity.Customer;
import com.bank.customer.onboarding.model.request.CustomerAccountOverviewRequest;
import com.bank.customer.onboarding.model.request.LogonRequest;
import com.bank.customer.onboarding.model.request.RegistrationRequest;
import com.bank.customer.onboarding.model.request.TokenRequest;
import com.bank.customer.onboarding.model.response.CustomerAccountOverviewResponse;
import com.bank.customer.onboarding.model.response.TokenResponse;
import com.bank.customer.onboarding.stubbuilder.RegistrationRequestBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CustomerAccountOverviewServiceImplTest {

    @Autowired
    private CustomerAccountOverviewService customerAccountOverviewService;

    @Autowired
    private CustomerDetailService customerDetailService;

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private AuthenticationService authenticationService;

    @Test
    void testCustomerAccountOverviewDetails() {
        RegistrationRequest registrationRequest = RegistrationRequestBuilder.createRegistrationRequest();
        registrationRequest.setUsername("USER_OVERVIEW_DETAIL");
        registrationService.register(registrationRequest);
        Customer customer = customerDetailService.findCustomerByName(registrationRequest.getUsername());
        TokenResponse tokenResponse = authenticationService.getToken(TokenRequest.builder()
                .username(customer.getName())
                .password(customer.getPassword()).build());
        assertNotNull(tokenResponse.getToken());
        authenticationService.login(LogonRequest.builder()
                .username(customer.getName())
                .password(customer.getPassword())
                .token(tokenResponse.getToken()).build());
        CustomerAccountOverviewResponse response = customerAccountOverviewService.getAccountOverview(
                CustomerAccountOverviewRequest.builder()
                        .customerId(customer.getId())
                        .token(tokenResponse.getToken()).build());
        assertEquals(1, response.getCustomerAccountOverviewList().size());
        assertEquals("Individual", response.getCustomerAccountOverviewList().get(0).getAccountType());
        assertEquals("EURO", response.getCustomerAccountOverviewList().get(0).getAccountCurrency());
        assertEquals(1L, response.getCustomerAccountOverviewList().get(0).getAccountNumber());
    }
}
