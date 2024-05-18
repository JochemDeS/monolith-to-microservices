package com.example.monolithtomicroservices.application.product;

import com.example.monolithtomicroservices.data.ProductDataFactory;
import com.example.monolithtomicroservices.domain.Product;
import com.example.monolithtomicroservices.domain.ProductId;

import java.util.Optional;

public class FakeGetProductByIdPort implements GetProductByIdPort {
    private final Product product = ProductDataFactory.createProducts().getFirst();

    @Override
    public Optional<Product> byId(ProductId id) {
        if (id.equals(product.id())) {
            return Optional.of(product);
        }
        return Optional.empty();
    }
}
