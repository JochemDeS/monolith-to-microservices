package com.example.monolithtomicroservices.application.product;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GetAllProductsUseCaseTest {
    private final FakeGetAllProductsPort fakeGetAllProductsPort = new FakeGetAllProductsPort();

    @Test
    void shouldReturnAllProducts() {
        final var target = new GetAllProductsUseCase(fakeGetAllProductsPort);

        ProductRequest request = ProductRequest.builder().build();
        final var result = target.handle(request);
        assertThat(result).isNotNull();
        assertThat(result).isNotEmpty();
    }
}