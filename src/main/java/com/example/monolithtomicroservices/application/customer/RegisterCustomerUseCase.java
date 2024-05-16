package com.example.monolithtomicroservices.application.customer;

import com.example.monolithtomicroservices.application.common.UseCase;
import com.example.monolithtomicroservices.domain.Address;
import com.example.monolithtomicroservices.domain.Customer;
import com.example.monolithtomicroservices.domain.Name;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class RegisterCustomerUseCase implements UseCase<RegisterCustomer, Customer> {
    private final FindCustomerPort findCustomerPort;
    private final SaveCustomerPort saveCustomerPort;

    public RegisterCustomerUseCase(FindCustomerPort findCustomerPort, SaveCustomerPort saveCustomerPort) {
        this.findCustomerPort = findCustomerPort;
        this.saveCustomerPort = saveCustomerPort;
    }

    @Override
    public Customer handle(RegisterCustomer request) {
        Assert.notNull(request, "Customer can not be null");

        final var customer = Customer.builder()
                .name(Name.builder()
                        .firstName(request.customer().firstName())
                        .lastName(request.customer().lastName())
                        .build())
                .address(Address.builder()
                        .street(request.address().street())
                        .houseNumber(request.address().houseNumber())
                        .city(request.address().city())
                        .zip(request.address().zip())
                        .country(request.address().country())
                        .build())
                .email(request.email())
                .build();

        findCustomerPort.byEmail(customer.email())
                .ifPresent(c -> {
                    throw new IllegalStateException("Email already exists");
                });

        return saveCustomerPort.save(customer);
    }
}
