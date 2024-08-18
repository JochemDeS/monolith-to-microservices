package com.example.monolithtomicroservices.infrastructure.http.cart;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UpdateCartModel(
        @NotNull @Positive
        long productId,
        @NotNull @Positive
        int quantity
) {
}
