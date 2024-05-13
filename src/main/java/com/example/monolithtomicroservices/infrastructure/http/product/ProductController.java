package com.example.monolithtomicroservices.infrastructure.http.product;

import com.example.monolithtomicroservices.application.common.UseCase;
import com.example.monolithtomicroservices.application.product.ProductRequest;
import com.example.monolithtomicroservices.domain.Product;
import com.example.monolithtomicroservices.domain.ProductId;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/products")
public class ProductController {
    private final UseCase<ProductRequest, Page<Product>> getAllProductsUseCase;
    private final UseCase<ProductId, Product> getProductByIdUseCase;

    public ProductController(UseCase<ProductRequest, Page<Product>> getAllProductsUseCase,
                             UseCase<ProductId, Product> getProductByIdUseCase) {
        this.getAllProductsUseCase = getAllProductsUseCase;
        this.getProductByIdUseCase = getProductByIdUseCase;
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
                .id(id)
                .build();

        final var product = getProductByIdUseCase.handle(productId);
        return mapToProductDetailModel(product);
    }

    private ProductReadModel mapToProductReadModel(Product product) {
        return ProductReadModel.builder()
                .id(product.id().id())
                .title(product.title())
                .description(product.description())
                .price(product.price())
                .discountPercentage(product.discountPercentage())
                .rating(product.rating())
                .brand(product.brand().name())
                .category(product.category().name())
                .thumbnail(product.thumbnail())
                .build();
    }

    private ProductDetailReadModel mapToProductDetailModel(Product product) {
        return ProductDetailReadModel.builder()
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
