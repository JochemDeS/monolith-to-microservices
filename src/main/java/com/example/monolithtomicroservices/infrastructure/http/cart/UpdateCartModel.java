package com.example.monolithtomicroservices.infrastructure.http.cart;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UpdateCartModel(
        @NotNull @Positive
        long productId,
        @NotNull @Min(0)
        int quantity
) {
}
