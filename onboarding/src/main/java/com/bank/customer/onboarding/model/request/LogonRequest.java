package com.bank.customer.onboarding.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LogonRequest {
    String username;
    String password;
    String token;
}
