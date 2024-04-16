package com.bank.customer.onboarding.service;

import com.bank.customer.onboarding.model.entity.Customer;
import com.bank.customer.onboarding.model.exception.CountryNotAllowedException;
import com.bank.customer.onboarding.model.exception.InsufficientAddressException;
import com.bank.customer.onboarding.model.exception.UserAgeNotAllowedException;
import com.bank.customer.onboarding.model.exception.UserExistException;
import com.bank.customer.onboarding.model.request.LogonRequest;
import com.bank.customer.onboarding.model.request.RegistrationRequest;
import com.bank.customer.onboarding.model.request.TokenRequest;
import com.bank.customer.onboarding.model.response.LogonResponse;
import com.bank.customer.onboarding.model.response.TokenResponse;
import com.bank.customer.onboarding.service.CustomerDetailService;
import com.bank.customer.onboarding.service.RegistrationService;
import com.bank.customer.onboarding.stubbuilder.RegistrationRequestBuilder;
import com.bank.customer.onboarding.util.Constants;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthenticationServiceImplTest {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private CustomerDetailService customerDetailService;

    @Test
    @Order(1)
    public void testGetToken() {
        RegistrationRequest registrationRequest = RegistrationRequestBuilder.createRegistrationRequest();
        registrationRequest.setUsername("tokenUser");
        registrationService.register(registrationRequest);
        Customer customer = customerDetailService.findCustomerByName(registrationRequest.getUsername());
        TokenResponse tokenResponse = authenticationService.getToken(TokenRequest.builder()
                .username(customer.getName())
                .password(customer.getPassword()).build());
        assertNotNull(tokenResponse.getToken());
    }

    @Test
    @Order(2)
    public void testLogin() {
        Customer customer = customerDetailService.findCustomerByName("tokenUser");
        assertFalse(customer.isSessionValid());
        LogonResponse logonResponse = authenticationService.login(LogonRequest.builder()
                .username(customer.getName())
                .password(customer.getPassword())
                .token(customer.getToken()).build());
        Customer updatedCustomer = customerDetailService.findCustomerByName("tokenUser");
        assertTrue(updatedCustomer.isSessionValid());
        assertEquals(Constants.LOGIN_SUCCESS_MESSAGE, logonResponse.getSuccess());
    }

    @Test
    @Order(2)
    public void testLogout() {
        Customer customer = customerDetailService.findCustomerByName("tokenUser");
        assertTrue(customer.isSessionValid());
        LogonResponse logonResponse = authenticationService.logout(LogonRequest.builder()
                .username(customer.getName())
                .password(customer.getPassword())
                .token(customer.getToken()).build());
        Customer updatedCustomer = customerDetailService.findCustomerByName("tokenUser");
        assertFalse(updatedCustomer.isSessionValid());
        assertEquals(Constants.LOGOUT_SUCCESS_MESSAGE, logonResponse.getSuccess());
    }
}
