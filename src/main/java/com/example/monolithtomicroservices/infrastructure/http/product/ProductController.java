package com.example.monolithtomicroservices.infrastructure.http.product;

import com.example.monolithtomicroservices.application.common.UseCase;
import com.example.monolithtomicroservices.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final UseCase<Pageable, Page<Product>> getAllProductsUseCase;

    public ProductController(UseCase<Pageable, Page<Product>> getAllProductsUseCase) {
        this.getAllProductsUseCase = getAllProductsUseCase;
    }
    @GetMapping
    public ProductsResponseModel getProducts(Pageable pageable) {
        Page<Product> products = getAllProductsUseCase.handle(pageable);
        return ProductsResponseModel.builder()
                .products(products.getContent().stream()
                        .map(this::mapToApplicationModel)
                        .toList())
                .page(products.getNumber())
                .size(products.getSize())
                .totalProducts((int) products.getTotalElements())
                .build();
    }

    private ProductReadModel mapToApplicationModel(Product product) {
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
