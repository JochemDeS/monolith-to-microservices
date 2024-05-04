package com.example.monolithtomicroservices.infrastructure.http.customer;

import com.example.monolithtomicroservices.application.common.UseCase;
import com.example.monolithtomicroservices.application.customer.RegisterCustomer;
import com.example.monolithtomicroservices.domain.Address;
import com.example.monolithtomicroservices.domain.Customer;
import com.example.monolithtomicroservices.domain.Name;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final UseCase<RegisterCustomer, Customer> registerCustomerUseCase;

    public CustomerController(UseCase<RegisterCustomer, Customer> registerCustomerUseCase) {
        this.registerCustomerUseCase = registerCustomerUseCase;
    }

    @PostMapping("/register")
    public void register(@RequestBody CustomerWriteModel request) {
        final var registerCustomer = RegisterCustomer.builder()
                .customer(Name.builder()
                        .firstName(request.firstName())
                        .lastName(request.lastName())
                        .build())
                .address(Address.builder()
                        .street(request.address().street())
                        .houseNumber(request.address().houseNumber())
                        .city(request.address().city())
                        .zip(request.address().zipCode())
                        .country(request.address().country())
                        .build())
                .email(request.email())
                .build();

        registerCustomerUseCase.handle(registerCustomer);
    }
}
