package com.example.monolithtomicroservices.infrastructure.persistence;

import com.example.monolithtomicroservices.domain.Category;
import com.example.monolithtomicroservices.domain.CategoryId;
import com.example.monolithtomicroservices.infrastructure.persistence.category.CategoryEntity;

public class CategoryMapper {
    public static Category toDomain(CategoryEntity categoryEntity) {
        return Category.builder()
                .id(CategoryId.builder()
                        .id(categoryEntity.getId())
                        .build())
                .name(categoryEntity.getName())
                .build();
    }

    public static CategoryEntity toEntity(Category category) {
        return CategoryEntity.builder()
                .id(category.id().id())
                .name(category.name())
                .build();
    }
}
