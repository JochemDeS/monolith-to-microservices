package com.example.monolithtomicroservices.infrastructure.http.cart;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UpdateCartModel(@NotNull @Min(0) Integer quantity) {
}
