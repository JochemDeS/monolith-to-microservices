package com.example.monolithtomicroservices.application.customer;

import com.example.monolithtomicroservices.domain.Customer;
import com.example.monolithtomicroservices.domain.CustomerId;

import java.util.Optional;

public interface GetCustomerByIdPort {
    Optional<Customer> byId(CustomerId id);
}
