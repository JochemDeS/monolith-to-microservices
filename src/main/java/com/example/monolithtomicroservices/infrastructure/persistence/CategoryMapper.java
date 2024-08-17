package com.example.monolithtomicroservices.infrastructure.persistence;

import com.example.monolithtomicroservices.domain.Category;
import com.example.monolithtomicroservices.domain.CategoryId;
import com.example.monolithtomicroservices.infrastructure.persistence.category.CategoryEntity;

public class CategoryMapper {
    public static Category mapToDomain(CategoryEntity categoryEntity) {
        return Category.builder()
                .id(CategoryId.builder()
                        .id(categoryEntity.getId())
                        .build())
                .name(categoryEntity.getName())
                .build();
    }
}
