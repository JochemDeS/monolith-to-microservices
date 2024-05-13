package com.example.monolithtomicroservices.infrastructure.http.product;

import com.example.monolithtomicroservices.application.common.UseCase;
import com.example.monolithtomicroservices.application.product.ProductRequest;
import com.example.monolithtomicroservices.domain.Product;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/products")
public class ProductController {
    private final UseCase<ProductRequest, Page<Product>> getAllProductsUseCase;

    public ProductController(UseCase<ProductRequest, Page<Product>> getAllProductsUseCase) {
        this.getAllProductsUseCase = getAllProductsUseCase;
    }

    @PostMapping
    public ProductsResponseModel getProducts(@Valid @RequestBody ProductRequestModel request,
                                             Pageable pageable) {
        ProductRequest productRequest = ProductRequest.builder()
                .category(request.category())
                .brand(request.brand())
                .priceRange(request.priceRange())
                .ratingRange(request.ratingRange())
                .pageable(pageable)
                .build();

        Page<Product> products = getAllProductsUseCase.handle(productRequest);

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
