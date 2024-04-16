package com.bank.customer.onboarding.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TokenRequest {
    String username;
    String password;
}
