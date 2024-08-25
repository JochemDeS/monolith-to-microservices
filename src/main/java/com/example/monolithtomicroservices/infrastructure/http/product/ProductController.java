package com.example.monolithtomicroservices.infrastructure.http.product;

import com.example.monolithtomicroservices.application.common.UseCase;
import com.example.monolithtomicroservices.application.product.ProductRequest;
import com.example.monolithtomicroservices.domain.Product;
import com.example.monolithtomicroservices.domain.ProductId;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/products")
@Tag(name = "Product", description = "All endpoints for the product")
public class ProductController {
    private final UseCase<ProductRequest, Page<Product>> getAllProductsUseCase;
    private final UseCase<ProductId, Product> getProductByIdUseCase;

    public ProductController(UseCase<ProductRequest, Page<Product>> getAllProductsUseCase,
                             UseCase<ProductId, Product> getProductByIdUseCase) {
        this.getAllProductsUseCase = getAllProductsUseCase;
        this.getProductByIdUseCase = getProductByIdUseCase;
    }

    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping
    public ProductsResponseModel getProducts(@Valid @RequestBody ProductRequestModel request,
                                             Pageable pageable) {
        ProductRequest productRequest = ProductRequest.builder()
                .category(request.category())
                .brand(request.brand())
                .priceRange(request.priceRange())
                .pageable(pageable)
                .build();

        Page<Product> products = getAllProductsUseCase.handle(productRequest);

        return ProductsResponseModel.builder()
                .products(products.getContent().stream()
                        .map(this::mapToProductReadModel)
                        .toList())
                .page(products.getNumber())
                .size(products.getSize())
                .totalProducts((int) products.getTotalElements())
                .build();
    }

    @GetMapping("/{id}")
    public ProductDetailReadModel getProduct(@PathVariable String id) {
        final var productId = ProductId.builder()
                .value(Long.parseLong(id))
                .build();

        final var product = getProductByIdUseCase.handle(productId);
        return mapToProductDetailModel(product);
    }

    private ProductReadModel mapToProductReadModel(Product product) {
        return ProductReadModel.builder()
                .id(product.id().value())
                .title(product.title())
                .description(product.description())
                .price(product.price())
                .brand(product.brand().name())
                .category(product.category().name())
                .thumbnail(product.thumbnail())
                .build();
    }

    private ProductDetailReadModel mapToProductDetailModel(Product product) {
        return ProductDetailReadModel.builder()
                .id(product.id().value())
                .title(product.title())
                .description(product.description())
                .price(product.price())
                .stock(product.stock())
                .brand(product.brand().name())
                .category(product.category().name())
                .thumbnail(product.thumbnail())
                .image(product.image())
                .build();
    }
}
