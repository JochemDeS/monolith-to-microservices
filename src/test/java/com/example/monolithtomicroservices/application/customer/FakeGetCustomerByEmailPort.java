package com.example.monolithtomicroservices.application.customer;

import com.example.monolithtomicroservices.domain.Customer;

import java.util.Optional;

public class FakeGetCustomerByEmailPort implements GetCustomerByEmailPort {
    private Customer customer;

    @Override
    public Optional<Customer> byEmail(String email) {
        return Optional.ofNullable(this.customer);
    }

    public void register(Customer customer) {
        this.customer = customer;
    }
}
