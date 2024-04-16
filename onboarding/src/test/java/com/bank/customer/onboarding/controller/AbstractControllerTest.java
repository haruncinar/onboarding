package com.bank.customer.onboarding.controller;


import com.bank.customer.onboarding.config.SecurityConfig;
import com.bank.customer.onboarding.service.*;
import com.bank.customer.onboarding.util.JwtAuthorizationFilter;
import com.bank.customer.onboarding.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(excludeAutoConfiguration  = SecurityConfig.class)
public abstract class AbstractControllerTest {

	@Autowired
	protected MockMvc mockMvc;

	@MockBean
	protected CustomerDetailService customerDetailService;

	@MockBean
	protected AuthenticationService authenticationService;

	@MockBean
	protected RegistrationService registrationService;

	@MockBean
	protected CustomerAccountOverviewService customerAccountOverviewService;

	@MockBean
	protected AllowedCountryService allowedCountryService;

	@MockBean
	protected JwtAuthorizationFilter jwtAuthorizationFilter;

	@MockBean
	protected JwtUtil jwtUtil;

	@BeforeEach
    public void setUp() {
		Mockito.reset(customerDetailService, authenticationService,
				registrationService, customerAccountOverviewService,
				allowedCountryService, jwtAuthorizationFilter, jwtUtil);
	}

}
