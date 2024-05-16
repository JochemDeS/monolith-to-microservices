package com.example.monolithtomicroservices.data;

import com.example.monolithtomicroservices.domain.Category;
import com.example.monolithtomicroservices.domain.CategoryId;

public enum CategoryDataFactory {
    SMARTPHONES(Category.builder()
            .id(CategoryId.builder()
                    .id(1L)
                    .build())
            .name("Smartphones")
            .build()),
    PERFUME(Category.builder()
            .id(CategoryId.builder()
                    .id(2L)
                    .build())
            .name("Parfum")
            .build());

    private final Category category;

    CategoryDataFactory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }
}
