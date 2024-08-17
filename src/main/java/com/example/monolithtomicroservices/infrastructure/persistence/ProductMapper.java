package com.example.monolithtomicroservices.infrastructure.persistence;

import com.example.monolithtomicroservices.domain.Product;
import com.example.monolithtomicroservices.domain.ProductId;
import com.example.monolithtomicroservices.infrastructure.persistence.product.ProductEntity;

public class ProductMapper {
    public static Product mapToProduct(ProductEntity productEntity) {
        return Product.builder()
                .id(ProductId.builder()
                        .value(productEntity.getId())
                        .build())
                .title(productEntity.getTitle())
                .description(productEntity.getDescription())
                .price(productEntity.getPrice())
                .stock(productEntity.getStock())
                .brand(BrandMapper.mapToDomain(productEntity.getBrand()))
                .category(CategoryMapper.mapToDomain(productEntity.getCategory()))
                .thumbnail(productEntity.getThumbnail())
                .image(productEntity.getImage())
                .build();
    }
}
