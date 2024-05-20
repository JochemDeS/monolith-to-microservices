package com.example.monolithtomicroservices.application.product;

import com.example.monolithtomicroservices.application.common.UseCase;
import com.example.monolithtomicroservices.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public class FakeGetAllProductsUseCase implements UseCase<ProductRequest, Page<Product>> {
    private Page<Product> products;

    @Override
    public Page<Product> handle(ProductRequest request) {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = new PageImpl<>(products);
    }
}
