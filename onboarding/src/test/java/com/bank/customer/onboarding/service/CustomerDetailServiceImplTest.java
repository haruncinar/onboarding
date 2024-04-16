package com.bank.customer.onboarding.service;

import com.bank.customer.onboarding.model.entity.Customer;
import com.bank.customer.onboarding.model.request.RegistrationRequest;
import com.bank.customer.onboarding.stubbuilder.RegistrationRequestBuilder;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerDetailServiceImplTest {

    @Autowired
    private CustomerDetailService customerDetailService;

    @Autowired
    private RegistrationService registrationService;

    @Test
    public void testUserDetailLoad() {
        RegistrationRequest registrationRequest = RegistrationRequestBuilder.createRegistrationRequest();
        registrationRequest.setUsername("TEST_USER");
        registrationService.register(registrationRequest);
        UserDetails customer = customerDetailService.loadUserByUsername(registrationRequest.getUsername());
        assertEquals("TEST_USER", customer.getUsername());
    }
}
