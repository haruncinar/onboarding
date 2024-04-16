package com.bank.customer.onboarding.controller;

import com.bank.customer.onboarding.model.exception.TooManyRequestException;
import com.bank.customer.onboarding.model.request.*;
import com.bank.customer.onboarding.model.response.*;
import com.bank.customer.onboarding.service.AllowedCountryService;
import com.bank.customer.onboarding.service.CustomerAccountOverviewService;
import com.bank.customer.onboarding.service.AuthenticationService;
import com.bank.customer.onboarding.service.RegistrationService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
public class OnboardingController {

    @Autowired
    private CustomerAccountOverviewService customerAccountOverviewService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private AllowedCountryService allowedCountryService;

    private final Bucket bucket;

    public OnboardingController() {
        Bandwidth limit = Bandwidth.classic(30, Refill.greedy(40, Duration.ofMinutes(1)));
        this.bucket = Bucket.builder()
                .addLimit(limit)
                .build();
    }


    @GetMapping("/overview")
    public CustomerAccountOverviewResponse getAccountOVerview(@RequestBody CustomerAccountOverviewRequest customerAccountOverviewRequest) {
        if (bucket.tryConsume(1)) {
            return customerAccountOverviewService.getAccountOverview(customerAccountOverviewRequest);
        }
        throw new TooManyRequestException();
    }

    @PostMapping("/token")
    public TokenResponse getToken(@RequestBody TokenRequest tokenRequest) {
        if (bucket.tryConsume(1)) {
            return authenticationService.getToken(tokenRequest);
        }
        throw new TooManyRequestException();
    }

    @PostMapping("/logon")
    public LogonResponse logon(@RequestBody LogonRequest logonRequest) {
        if (bucket.tryConsume(1)) {
            return authenticationService.login(logonRequest);
        }
        throw new TooManyRequestException();
    }

    @PostMapping("/logout")
    public LogonResponse logout(@RequestBody LogonRequest logonRequest) {
        if (bucket.tryConsume(1)) {
            return authenticationService.logout(logonRequest);
        }
        throw new TooManyRequestException();
    }

    @PostMapping("/register")
    public RegistrationResponse register(@RequestBody RegistrationRequest registrationRequest) {
        if (bucket.tryConsume(1)) {
            return registrationService.register(registrationRequest);
        }
        throw new TooManyRequestException();
    }

    @PostMapping("/addCountry")
    public AllowedCountryResponse addCountry(@RequestBody AllowedCountryRequest allowedCountryRequest) {
        if (bucket.tryConsume(1)) {
            return allowedCountryService.save(allowedCountryRequest);
        }
        throw new TooManyRequestException();
    }
}
