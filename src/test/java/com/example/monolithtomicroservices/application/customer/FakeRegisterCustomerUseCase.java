package com.example.monolithtomicroservices.application.customer;

import com.example.monolithtomicroservices.application.common.UseCase;
import com.example.monolithtomicroservices.domain.Customer;
import com.example.monolithtomicroservices.domain.Name;

public class FakeRegisterCustomerUseCase implements UseCase<RegisterCustomer, Customer> {
    private Customer customer;

    @Override
    public Customer handle(RegisterCustomer request) {
        final var customer = Customer.builder()
                .name(Name.builder()
                        .firstName(request.customer().firstName())
                        .lastName(request.customer().lastName())
                        .build())
                .email(request.email())
                .address(request.address())
                .build();
        this.customer = customer;
        return customer;
    }

    public Customer getCustomer() {
        return customer;
    }
}
