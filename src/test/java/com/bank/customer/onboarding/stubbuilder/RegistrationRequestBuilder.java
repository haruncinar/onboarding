package com.bank.customer.onboarding.stubbuilder;

import com.bank.customer.onboarding.model.dto.AddressDto;
import com.bank.customer.onboarding.model.dto.IdDocumentDto;
import com.bank.customer.onboarding.model.request.RegistrationRequest;

import java.time.LocalDate;

public class RegistrationRequestBuilder {
    public static RegistrationRequest createRegistrationRequest() {
        AddressDto addressDto = AddressDto.builder()
                .city("Utrecht")
                .country("Netherlands")
                .doorNumber("10")
                .street("Mijdrechtstraat")
                .postalCode("1010AB").build();

        IdDocumentDto idDocumentDto = IdDocumentDto.builder()
                .citizenId(12345678L)
                .nationality("Dutch").build();

        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setBirthday(LocalDate.of(1990,10,10));
        registrationRequest.setUsername("harun");
        registrationRequest.setAddressDto(addressDto);
        registrationRequest.setIdDocumentDto(idDocumentDto);
        return registrationRequest;
    }
}
