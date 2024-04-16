package com.bank.customer.onboarding.service;

import com.bank.customer.onboarding.model.entity.Customer;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomerDetailService extends UserDetailsService {

    Customer findCustomerByNameAndPassword(String name, String password);

    Customer findCustomerByName(String name);

    Customer findCustomerByNameAndPasswordAndToken(String name, String password, String token);

    Customer save(Customer customer);

    boolean checkUserExistence(String name);
}
