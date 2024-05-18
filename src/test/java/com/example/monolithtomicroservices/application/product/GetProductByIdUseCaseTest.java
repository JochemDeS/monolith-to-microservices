package com.example.monolithtomicroservices.application.product;

import com.example.monolithtomicroservices.application.exception.ResourceNotFoundException;
import com.example.monolithtomicroservices.domain.ProductId;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GetProductByIdUseCaseTest {
    private final FakeGetProductByIdPort fakeGetProductByIdPort = new FakeGetProductByIdPort();

    @Test
    void shouldReturnProductById() {
        final var target = new GetProductByIdUseCase(fakeGetProductByIdPort);
        final var expected = ProductId.builder()
                .id(1L)
                .build();

        final var result = target.handle(expected);

        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(expected);
    }

    @Test
    void shouldThrowExceptionWhenProductNotFound() {
        final var target = new GetProductByIdUseCase(fakeGetProductByIdPort);
        final var expected = ProductId.builder()
                .id(99L)
                .build();

        assertThatThrownBy(() -> target.handle(expected)).isInstanceOf(ResourceNotFoundException.class);
    }
}