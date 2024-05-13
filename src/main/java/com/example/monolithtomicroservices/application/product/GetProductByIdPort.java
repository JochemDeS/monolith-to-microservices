package com.example.monolithtomicroservices.application.product;

import com.example.monolithtomicroservices.domain.Product;
import com.example.monolithtomicroservices.domain.ProductId;

import java.util.Optional;

public interface GetProductByIdPort {
    Optional<Product> byId(ProductId id);
}
