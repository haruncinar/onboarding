package com.bank.customer.onboarding.model.request;

import com.bank.customer.onboarding.model.dto.AddressDto;
import com.bank.customer.onboarding.model.dto.IdDocumentDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RegistrationRequest {

    private String username;

    private AddressDto addressDto;

    private LocalDate birthday;

    private IdDocumentDto idDocumentDto;
}
