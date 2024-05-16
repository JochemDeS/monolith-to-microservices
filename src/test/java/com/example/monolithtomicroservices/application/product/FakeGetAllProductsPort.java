package com.example.monolithtomicroservices.application.product;

import com.example.monolithtomicroservices.data.ProductDataFactory;
import com.example.monolithtomicroservices.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public class FakeGetAllProductsPort implements GetAllProductsPort {
    private final List<Product> productList = ProductDataFactory.createProducts();
    private final Page<Product> products = new PageImpl<>(productList, PageRequest.of(0, 3), productList.size());

    @Override
    public Page<Product> all(ProductRequest request) {
        return this.products;
    }
}
