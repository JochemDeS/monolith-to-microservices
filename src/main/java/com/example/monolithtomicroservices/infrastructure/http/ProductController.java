package com.example.monolithtomicroservices.infrastructure.http;

import com.example.monolithtomicroservices.application.product.GetAllProductsUseCase;
import com.example.monolithtomicroservices.domain.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final GetAllProductsUseCase getAllProductsUseCase;

    public ProductController(GetAllProductsUseCase getAllProductsUseCase) {
        this.getAllProductsUseCase = getAllProductsUseCase;
    }
    @GetMapping
    public List<Product> getProducts() {
        return getAllProductsUseCase.handle();
    }
}
