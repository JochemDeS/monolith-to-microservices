package com.example.monolithtomicroservices.application.product;

import com.example.monolithtomicroservices.domain.Product;

import java.util.List;

public interface GetAllProductsPort {
    List<Product> all();
}
