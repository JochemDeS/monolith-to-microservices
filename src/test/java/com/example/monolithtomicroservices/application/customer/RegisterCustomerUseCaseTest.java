package com.example.monolithtomicroservices.application.customer;

import com.example.monolithtomicroservices.domain.Address;
import com.example.monolithtomicroservices.domain.Customer;
import com.example.monolithtomicroservices.domain.Name;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RegisterCustomerUseCaseTest {
    private FakeGetCustomerByEmailPort fakeGetCustomerByEmailPort = new FakeGetCustomerByEmailPort();
    private FakeSaveCustomerPort fakeSaveCustomerPort = new FakeSaveCustomerPort();

    @Test
    void shouldRegisterNewCustomer() {
//        final var customer = Customer.builder()
//                .name(Name.builder()
//                                .firstName("John")
//                                .lastName("Doe")
//                                .build())
//                .email("john.doe@test.com")
//                .address(Address.builder()
//                        .street("Main Street")
//                        .houseNumber("1")
//                        .city("Springfield")
//                        .zip("12345")
//                        .country("USA")
//                        .build())
//                .build();
//        fakeGetCustomerByEmailPort.register(customer);

//        final var request = RegisterCustomer.builder()
//                .customer(customer.name())
//                .email(customer.email())
//                .address(customer.address())
//                .build();

        final var request = RegisterCustomer.builder()
                .customer(Name.builder()
                        .firstName("John")
                        .lastName("Doe")
                        .build())
                .email("john.doe@test.com")
                .address(Address.builder()
                        .street("Main Street")
                        .houseNumber("1")
                        .city("Springfield")
                        .zip("12345")
                        .country("USA")
                        .build())
                .build();

        final var target = new RegisterCustomerUseCase(fakeGetCustomerByEmailPort, fakeSaveCustomerPort);
        target.handle(request);

        final var result = fakeSaveCustomerPort.getCustomer();
        assertThat(result).isNotNull();
    }

}