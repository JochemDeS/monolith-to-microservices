package com.example.monolithtomicroservices.application.customer;

import com.example.monolithtomicroservices.domain.Customer;

public interface SaveCustomerPort {
    Customer save(Customer customer);
}
