package com.bank.customer.onboarding.service;

import com.bank.customer.onboarding.model.entity.Customer;
import com.bank.customer.onboarding.model.exception.CountryNotAllowedException;
import com.bank.customer.onboarding.model.exception.InsufficientAddressException;
import com.bank.customer.onboarding.model.exception.UserAgeNotAllowedException;
import com.bank.customer.onboarding.model.exception.UserExistException;
import com.bank.customer.onboarding.model.request.RegistrationRequest;
import com.bank.customer.onboarding.service.CustomerDetailService;
import com.bank.customer.onboarding.service.RegistrationService;
import com.bank.customer.onboarding.stubbuilder.RegistrationRequestBuilder;
import com.bank.customer.onboarding.util.Constants;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RegistrationServiceImplTest {

    @Autowired
    RegistrationService registrationService;

    @Autowired
    CustomerDetailService customerDetailService;

    @Test
    @Order(1)
    public void testAccountSave() {
        registrationService.register(RegistrationRequestBuilder.createRegistrationRequest());
        Customer customer = customerDetailService.findCustomerByName("harun");
        assertNotNull(customer.getAddress());
        assertNotNull(customer.getIdDocument());
        assertEquals("Netherlands", customer.getAddress().getCountry());
        assertEquals("Utrecht", customer.getAddress().getCity());
        assertEquals("1010AB", customer.getAddress().getPostalCode());
        assertEquals("10", customer.getAddress().getDoorNumber());
        assertEquals("Mijdrechtstraat", customer.getAddress().getStreet());
        assertEquals("Dutch", customer.getIdDocument().getNationality());
        assertEquals(LocalDate.of(1990,10,10), customer.getBirthday());
    }

    @Test
    @Order(2)
    public void ageValidationFailure() {
        RegistrationRequest registrationRequest = RegistrationRequestBuilder.createRegistrationRequest();
        registrationRequest.setBirthday(LocalDate.of(2015,10,10));
        registrationRequest.setUsername("testUser");

        Exception exception = assertThrows(UserAgeNotAllowedException.class, () -> {
            registrationService.register(registrationRequest);
        });
        Assertions.assertEquals(Constants.USER_AGE_NOT_ALLOWED_FAILURE_MESSAGE, exception.getMessage());
    }

    @Test
    @Order(3)
    public void userExistFailure() {
        RegistrationRequest registrationRequest = RegistrationRequestBuilder.createRegistrationRequest();

        Exception exception = assertThrows(UserExistException.class, () -> {
            registrationService.register(registrationRequest);
        });
        assertEquals(Constants.USER_EXIST_FAILURE_MESSAGE, exception.getMessage());
    }

    @Test
    @Order(4)
    public void insufficientAddressInfoFailure() {
        RegistrationRequest registrationRequest = RegistrationRequestBuilder.createRegistrationRequest();
        registrationRequest.setUsername("test-user");
        registrationRequest.getAddressDto().setCity(null);
        Exception exception = assertThrows(InsufficientAddressException.class, () -> {
            registrationService.register(registrationRequest);
        });
        assertEquals(Constants.INSUFFICIENT_ADDRESS_INFO_MESSAGE, exception.getMessage());
    }

    @Test
    @Order(5)
    public void countryNotAllowedFailure() {
        RegistrationRequest registrationRequest = RegistrationRequestBuilder.createRegistrationRequest();
        registrationRequest.setUsername("test-user1");
        registrationRequest.getAddressDto().setCountry("Greece");
        Exception exception = assertThrows(CountryNotAllowedException.class, () -> {
            registrationService.register(registrationRequest);
        });
        assertEquals(Constants.COUNTRY_NOT_ALLOWED_MESSAGE, exception.getMessage());
    }

}
