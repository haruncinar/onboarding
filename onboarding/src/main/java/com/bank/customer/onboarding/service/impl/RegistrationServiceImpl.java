package com.bank.customer.onboarding.service.impl;

import com.bank.customer.onboarding.model.dto.AddressDto;
import com.bank.customer.onboarding.model.dto.IdDocumentDto;
import com.bank.customer.onboarding.model.entity.*;
import com.bank.customer.onboarding.model.enums.AccountType;
import com.bank.customer.onboarding.model.exception.CountryNotAllowedException;
import com.bank.customer.onboarding.model.exception.InsufficientAddressException;
import com.bank.customer.onboarding.model.exception.UserAgeNotAllowedException;
import com.bank.customer.onboarding.model.exception.UserExistException;
import com.bank.customer.onboarding.model.request.RegistrationRequest;
import com.bank.customer.onboarding.model.response.RegistrationResponse;
import com.bank.customer.onboarding.service.*;
import com.bank.customer.onboarding.util.Constants;
import com.bank.customer.onboarding.util.IbanGenerationUtil;
import com.bank.customer.onboarding.util.PasswordGenerationUtil;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final AllowedCountryService allowedCountryService;

    private final AddressService addressService;

    private final IdDocumentService idDocumentService;

    private final CurrencyService currencyService;

    private final CustomerDetailService customerDetailService;


    @Override
    @Transactional
    public RegistrationResponse register(RegistrationRequest registrationRequest) {
        validateRegistration(registrationRequest.getUsername(), registrationRequest.getBirthday(), registrationRequest.getAddressDto());
        Customer customer = new Customer();
        mapCustomer(customer, registrationRequest);
        addressService.save(customer.getAddress());
        idDocumentService.save(customer.getIdDocument());
        Customer savedCustomer = customerDetailService.save(customer);
        createAndAddDefaultAccount(savedCustomer);
        customerDetailService.save(savedCustomer);

        return RegistrationResponse.builder().success(Constants.REGISTER_SUCCESSFUL_MESSAGE).build();
    }

    private void validateRegistration(String username, LocalDate birthday, AddressDto addressDto) {
        boolean userExist = customerDetailService.checkUserExistence(username);
        if(userExist) {
            throw new UserExistException();
        }
        int age = calculateAge(birthday, LocalDate.now());
        if(age < 18) {
            throw new UserAgeNotAllowedException();
        }
        if(addressDto == null || addressDto.getCountry() == null || addressDto.getStreet() == null
                || addressDto.getCity() == null || addressDto.getPostalCode() == null || addressDto.getDoorNumber() == null ) {
            throw new InsufficientAddressException();
        }
        List<String> allowedCountriesNames = allowedCountryService.getCountries().stream().map(AllowedCountry::getCountryName).toList();
        if(!allowedCountriesNames.contains(addressDto.getCountry())) {
            throw new CountryNotAllowedException();
        }
    }

    private int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }

    private void mapCustomer(Customer customer, RegistrationRequest registrationRequest) {
        customer.setName(registrationRequest.getUsername());
        customer.setPassword(PasswordGenerationUtil.generatePassword());
        customer.setBirthday(registrationRequest.getBirthday());
        mapAddressInfo(customer, registrationRequest.getAddressDto());
        mapIdDocumentInfo(customer, registrationRequest.getIdDocumentDto());
    }

    private void mapAddressInfo(Customer customer, AddressDto addressDto) {
        Address address = new Address();
        address.setCity(addressDto.getCity());
        address.setCountry(addressDto.getCountry());
        address.setDoorNumber(addressDto.getDoorNumber());
        address.setPostalCode(addressDto.getPostalCode());
        address.setStreet(addressDto.getStreet());
        customer.setAddress(address);
    }

    private void mapIdDocumentInfo(Customer customer, IdDocumentDto idDocumentDto) {
        IdDocument idDocument = new IdDocument();
        idDocument.setCitizenId(idDocumentDto.getCitizenId());
        idDocument.setNationality(idDocumentDto.getNationality());
        customer.setIdDocument(idDocument);
    }

    private void createAndAddDefaultAccount(Customer customer) {
        Currency currency = currencyService.getCurrencyByName(Constants.EURO);
        String allowedCountryCode = allowedCountryService.getCountryCodeByCountryName(customer.getAddress().getCountry());
        Account account = new Account();
        account.setAccountBalance(BigDecimal.ZERO);
        account.setAccountType(AccountType.Individual);
        account.setIban(IbanGenerationUtil.createRandomIban(allowedCountryCode));
        account.setCustomer(customer);
        account.setCurrency(currency);
        customer.getAccounts().add(account);
    }
}
