package com.example.monolithtomicroservices.infrastructure.persistence.product;

import com.example.monolithtomicroservices.application.product.GetAllProductsPort;
import com.example.monolithtomicroservices.application.product.GetProductByIdPort;
import com.example.monolithtomicroservices.application.product.ProductRequest;
import com.example.monolithtomicroservices.domain.*;
import com.example.monolithtomicroservices.infrastructure.persistence.brand.BrandEntity;
import com.example.monolithtomicroservices.infrastructure.persistence.category.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class ProductSqlPersistenceAdapter implements GetAllProductsPort, GetProductByIdPort {
    private final ProductRepository productRepository;

    public ProductSqlPersistenceAdapter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<Product> all(ProductRequest request) {
        Specification<ProductEntity> specification = Specification.where(ProductSpecs.byCategory(request.category()))
                        .and(ProductSpecs.byBrand(request.brand()))
                        .and(ProductSpecs.byPriceRange(request.priceRange()));
        return productRepository.findAll(specification, request.pageable()).map(this::toDomain);
    }

    private Product toDomain(ProductEntity productEntity) {
        return Product.builder()
                .id(ProductId.builder()
                        .id(productEntity.getId())
                        .build())
                .title(productEntity.getTitle())
                .description(productEntity.getDescription())
                .price(productEntity.getPrice())
                .stock(productEntity.getStock())
                .brand(toDomain(productEntity.getBrand()))
                .category(toDomain(productEntity.getCategory()))
                .thumbnail(productEntity.getThumbnail())
                .image(productEntity.getImage())
                .build();
    }

    @Override
    public Optional<Product> byId(ProductId id) {
        return productRepository.findById(id.id()).map(this::toDomain);
    }

    private Category toDomain(CategoryEntity categoryEntity) {
        return Category.builder()
                .id(CategoryId.builder()
                        .id(categoryEntity.getId())
                        .build())
                .name(categoryEntity.getName())
                .build();
    }

    private Brand toDomain(BrandEntity brandEntity) {
        return Brand.builder()
                .id(BrandId.builder()
                        .id(brandEntity.getId())
                        .build())
                .name(brandEntity.getName())
                .build();
    }
}
