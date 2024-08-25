package com.example.monolithtomicroservices.infrastructure.persistence.product;

import com.example.monolithtomicroservices.domain.PriceRange;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;
import java.util.function.BiFunction;

public class ProductSpecs {
    public static Specification<ProductEntity> byCategory(String category) {
        return createSpec(category, (root, criteriaBuilder) -> criteriaBuilder.equal(
                criteriaBuilder.lower(root.get("category").get("name")), category.toLowerCase()));
    }

    public static Specification<ProductEntity> byBrand(String brand) {
        return createSpec(brand, (root, criteriaBuilder) -> criteriaBuilder.equal(
                criteriaBuilder.lower(root.get("brand").get("name")), brand.toLowerCase()));
    }

    public static Specification<ProductEntity> byPriceRange(PriceRange priceRange) {
        return createSpec(priceRange, (root, criteriaBuilder) -> criteriaBuilder.between(root.get("price"), priceRange.min(), priceRange.max()));
    }

    private static <T> Specification<ProductEntity> createSpec(T value, BiFunction<Root<ProductEntity>, CriteriaBuilder, Predicate> function) {
        return (root, query, criteriaBuilder) -> Optional.ofNullable(value)
                .map(v -> function.apply(root, criteriaBuilder))
                .orElse(criteriaBuilder.conjunction());
    }
}
