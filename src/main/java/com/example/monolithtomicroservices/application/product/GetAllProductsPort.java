package com.example.monolithtomicroservices.application.product;

import com.example.monolithtomicroservices.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetAllProductsPort {
    Page<Product> all(Pageable pageable);
}
