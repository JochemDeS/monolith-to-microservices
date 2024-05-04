package com.example.monolithtomicroservices.infrastructure.http.product;

import com.example.monolithtomicroservices.application.common.ReturnUseCase;
import com.example.monolithtomicroservices.domain.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ReturnUseCase<List<Product>> getAllProductsUseCase;

    public ProductController(ReturnUseCase<List<Product>> getAllProductsUseCase) {
        this.getAllProductsUseCase = getAllProductsUseCase;
    }
    @GetMapping
    public List<ProductReadModel> getProducts() {
        List<Product> products = getAllProductsUseCase.handle();
        return products.stream()
                .map(this::fromDomain)
                .toList();
    }

    private ProductReadModel fromDomain(Product product) {
        return ProductReadModel.builder()
                .id(product.id().id())
                .title(product.title())
                .description(product.description())
                .price(product.price())
                .discountPercentage(product.discountPercentage())
                .rating(product.rating())
                .stock(product.stock())
                .brand(product.brand().name())
                .category(product.category().name())
                .thumbnail(product.thumbnail())
                .image(product.image())
                .build();
    }
}
