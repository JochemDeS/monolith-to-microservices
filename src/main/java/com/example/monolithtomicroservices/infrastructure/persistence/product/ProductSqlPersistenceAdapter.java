package com.example.monolithtomicroservices.infrastructure.persistence.product;

import com.example.monolithtomicroservices.application.product.GetAllProductsPort;
import com.example.monolithtomicroservices.domain.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductSqlPersistenceAdapter implements GetAllProductsPort {
    @Override
    public List<Product> getAllProducts() {
        return null;
    }
}
