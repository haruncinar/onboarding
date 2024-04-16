package com.bank.customer.onboarding.repository;

import com.bank.customer.onboarding.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.accounts WHERE c.id = :customerId and c.token = :token and c.sessionValid is true ")
    Optional<Customer> getCustomerWithAccountDetails(@Param("customerId") Long customerId, @Param("token") String token);

    Optional<Customer> findCustomerByNameAndPassword(String name, String password);

    Optional<Customer> findCustomerByName(String name);

    Optional<Customer> findCustomerByNameAndPasswordAndToken(String name, String password, String token);

}
