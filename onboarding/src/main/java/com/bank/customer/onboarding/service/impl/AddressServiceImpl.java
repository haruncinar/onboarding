package com.bank.customer.onboarding.service.impl;

import com.bank.customer.onboarding.model.entity.Address;
import com.bank.customer.onboarding.repository.AddressRepository;
import com.bank.customer.onboarding.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }
}
