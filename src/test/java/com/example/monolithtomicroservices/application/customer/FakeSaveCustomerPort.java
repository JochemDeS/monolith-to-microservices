package com.example.monolithtomicroservices.application.customer;

import com.example.monolithtomicroservices.domain.Customer;

public class FakeSaveCustomerPort implements SaveCustomerPort {
    private Customer customer;

    @Override
    public Customer save(Customer customer) {
        this.customer = customer;
        return this.customer;
    }

    public Customer getCustomer() {
        return customer;
    }
}
