package com.bank.customer.onboarding.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class IdDocumentDto {

    private Long citizenId;

    private String nationality;
}
