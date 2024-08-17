package com.example.monolithtomicroservices.application.product;

import com.example.monolithtomicroservices.application.common.UseCase;
import com.example.monolithtomicroservices.application.exception.ResourceNotFoundException;
import com.example.monolithtomicroservices.domain.Product;
import com.example.monolithtomicroservices.domain.ProductId;

public class FakeGetProductByIdUseCase implements UseCase<ProductId, Product> {
    private Product product;

    @Override
    public Product handle(ProductId request) {
        if (request.value() == product.id().value())
            return product;
        else
            throw new ResourceNotFoundException("Product not found");
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
