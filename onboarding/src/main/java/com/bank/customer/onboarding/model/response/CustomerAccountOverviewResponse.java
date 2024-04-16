package com.bank.customer.onboarding.model.response;

import com.bank.customer.onboarding.model.dto.CustomerAccountOverview;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Builder
@Getter
@Setter
public class CustomerAccountOverviewResponse {
    List<CustomerAccountOverview> customerAccountOverviewList;
}
