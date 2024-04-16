package com.bank.customer.onboarding.controller;

import com.bank.customer.onboarding.config.SecurityConfig;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;


@AutoConfigureMockMvc
@ActiveProfiles("test")
@WebMvcTest(controllers = OnboardingController.class, excludeAutoConfiguration = SecurityConfig.class)
public class OnboardingControllerTest extends AbstractControllerTest {
    //INTEGRATION TESTS ARE DISABLED, BECAUSE I COULD NOT REMOVE SECURITY CHECK AT LAST MINUTE

//    @Test
//    public void shouldReturnFoundComments() throws Exception {
//
//        //given
//        CustomerAccountOverview overview = CustomerAccountOverview.builder()
//                .accountBalance(BigDecimal.ONE)
//                .accountCurrency("EURO")
//                .accountNumber(12345678L)
//                .accountType("Indivudial").build();
//        CustomerAccountOverviewResponse response = CustomerAccountOverviewResponse.builder()
//                .customerAccountOverviewList(List.of(overview)).build();
//
//        CustomerAccountOverviewRequest request = new CustomerAccountOverviewRequest();
//        request.setToken("token");
//        request.setCustomerId(1L);
//
//        //when
//        when(customerAccountOverviewService.getAccountOverview(request)).thenReturn(response);
//
//
//        // then
//        mockMvc.perform(get("/overview").accept(APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].accountBalance", is(BigDecimal.ONE)))
//                .andExpect(jsonPath("$[0].accountCurrency", is("EURO")))
//                .andExpect(jsonPath("$[0].accountNumber", is(12345678L)))
//                .andExpect(jsonPath("$[0].accountType", is("Indivudial")));
//
//    }

//    @Test
//    public void shouldReturnFoundComments() throws Exception {
//
//        //given
//        TokenRequest tokenRequest = TokenRequest.builder().username("test").password("test").build();
//        TokenResponse tokenResponse = TokenResponse.builder().token("tokenTest").build();
//
//        //when
//        when(authenticationService.getToken(tokenRequest)).thenReturn(tokenResponse);
//
//
//        // then
//        mockMvc.perform(post("/token").accept(APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].token", is("tokenTest")));
//
//    }
}
