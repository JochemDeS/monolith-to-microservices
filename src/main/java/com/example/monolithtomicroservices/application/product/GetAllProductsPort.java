package com.example.monolithtomicroservices.application.product;

import com.example.monolithtomicroservices.domain.Product;
import org.springframework.data.domain.Page;

public interface GetAllProductsPort {
    Page<Product> all(ProductRequest request);
}
