package com.bank.customer.onboarding.service.impl;

import com.bank.customer.onboarding.model.entity.Customer;
import com.bank.customer.onboarding.model.exception.LoginException;
import com.bank.customer.onboarding.model.exception.UserNotFoundException;
import com.bank.customer.onboarding.repository.CustomerRepository;
import com.bank.customer.onboarding.service.CustomerDetailService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerDetailServiceImpl implements CustomerDetailService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer findCustomerByNameAndPassword(String name, String password) {
        Optional<Customer> customerOptional = customerRepository.findCustomerByNameAndPassword(name, password);
        if(customerOptional.isEmpty()) {
            throw new UserNotFoundException();
        }
        return customerOptional.get();
    }

    @Override
    public Customer findCustomerByName(String name) {
        Optional<Customer> customerOptional = customerRepository.findCustomerByName(name);
        if(customerOptional.isEmpty()) {
            throw new UserNotFoundException();
        }
        return customerOptional.get();
    }

    @Override
    public Customer findCustomerByNameAndPasswordAndToken(String name, String password, String token) {
        Optional<Customer> customerOptional = customerRepository.findCustomerByNameAndPasswordAndToken(name, password, token);
        if(customerOptional.isEmpty()) {
            throw new LoginException();
        }
        return customerOptional.get();
    }

    @Override
    @Transactional
    public Customer save(Customer customer) {
        return customerRepository.saveAndFlush(customer);
    }

    @Override
    public boolean checkUserExistence(String name) {
        Optional<Customer> customerOptional = customerRepository.findCustomerByName(name);
        return customerOptional.isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = findCustomerByName(username);
        return org.springframework.security.core.userdetails.User.builder()
                        .username(customer.getName())
                        .password(customer.getPassword())
                        .build();
    }
}
