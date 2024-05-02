package com.example.monolithtomicroservices.infrastructure.persistence.product;

import com.example.monolithtomicroservices.application.product.GetAllProductsPort;
import com.example.monolithtomicroservices.domain.*;
import com.example.monolithtomicroservices.infrastructure.persistence.brand.BrandEntity;
import com.example.monolithtomicroservices.infrastructure.persistence.category.CategoryEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductSqlPersistenceAdapter implements GetAllProductsPort {
    private final ProductRepository productRepository;

    public ProductSqlPersistenceAdapter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> all() {
        return productRepository.findAll().stream()
                .map(this::toDomain)
                .toList();
    }

    private Product toDomain(ProductEntity productEntity) {
        return Product.builder()
                .id(ProductId.builder()
                        .id(productEntity.getId())
                        .build())
                .title(productEntity.getTitle())
                .description(productEntity.getDescription())
                .price(productEntity.getPrice())
                .discountPercentage(productEntity.getDiscountPercentage())
                .rating(productEntity.getRating())
                .stock(productEntity.getStock())
                .brand(toDomain(productEntity.getBrand()))
                .category(toDomain(productEntity.getCategory()))
                .thumbnail(productEntity.getThumbnail())
                .image(productEntity.getImage())
                .build();
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
