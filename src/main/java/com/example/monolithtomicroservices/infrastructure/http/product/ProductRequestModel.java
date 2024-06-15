package com.example.monolithtomicroservices.infrastructure.http.product;

import com.example.monolithtomicroservices.domain.PriceRange;
import jakarta.annotation.Nullable;

import java.util.Optional;

public record ProductRequestModel(
        @Nullable String category,
        @Nullable String brand,
        @Nullable PriceRange priceRange
) {
    public ProductRequestModel {
        category = Optional.ofNullable(category)
                .filter(s -> !s.isBlank())
                .orElse(null);

        brand = Optional.ofNullable(brand)
                .filter(s -> !s.isBlank())
                .orElse(null);
    }
}
