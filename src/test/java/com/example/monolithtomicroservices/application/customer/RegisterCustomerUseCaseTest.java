package com.example.monolithtomicroservices.application.customer;

import com.example.monolithtomicroservices.domain.Address;
import com.example.monolithtomicroservices.domain.Customer;
import com.example.monolithtomicroservices.domain.Name;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RegisterCustomerUseCaseTest {
    private final FakeGetCustomerByEmailPort fakeGetCustomerByEmailPort = new FakeGetCustomerByEmailPort();
    private final FakeSaveCustomerPort fakeSaveCustomerPort = new FakeSaveCustomerPort();

    private final Customer customer = Customer.builder()
            .name(Name.builder()
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

    @Test
    void shouldRegisterNewCustomer() {
        final var request = RegisterCustomer.builder()
                .customer(this.customer.name())
                .email(this.customer.email())
                .address(this.customer.address())
                .build();

        final var target = new RegisterCustomerUseCase(fakeGetCustomerByEmailPort, fakeSaveCustomerPort);
        target.handle(request);

        final var result = fakeSaveCustomerPort.getCustomer();
        assertThat(result).isNotNull();
    }

    @Test
    void shouldThrowExceptionWhenEmailAlreadyExists() {
        fakeGetCustomerByEmailPort.register(this.customer);
        final var request = RegisterCustomer.builder()
                .customer(this.customer.name())
                .email(this.customer.email())
                .address(this.customer.address())
                .build();

        final var target = new RegisterCustomerUseCase(fakeGetCustomerByEmailPort, fakeSaveCustomerPort);
        assertThatThrownBy(() -> target.handle(request)).isInstanceOf(IllegalStateException.class);
        assertThat(fakeSaveCustomerPort.getCustomer()).isNull();
    }

    @Test
    void shouldThrowExceptionWhenCustomerIsNull() {
        final var target = new RegisterCustomerUseCase(fakeGetCustomerByEmailPort, fakeSaveCustomerPort);
        assertThatThrownBy(() -> target.handle(null)).isInstanceOf(IllegalArgumentException.class);
        assertThat(fakeSaveCustomerPort.getCustomer()).isNull();
    }

}