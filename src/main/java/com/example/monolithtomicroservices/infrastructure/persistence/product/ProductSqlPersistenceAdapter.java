package com.example.monolithtomicroservices.infrastructure.persistence.product;

import com.example.monolithtomicroservices.application.product.GetAllProductsPort;
import com.example.monolithtomicroservices.application.product.GetProductByIdPort;
import com.example.monolithtomicroservices.application.product.GetProductsByIdInPort;
import com.example.monolithtomicroservices.application.product.ProductRequest;
import com.example.monolithtomicroservices.domain.*;
import com.example.monolithtomicroservices.infrastructure.persistence.ProductMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class ProductSqlPersistenceAdapter implements GetAllProductsPort, GetProductByIdPort, GetProductsByIdInPort {
    private final ProductRepository productRepository;

    public ProductSqlPersistenceAdapter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<Product> all(ProductRequest request) {
        Specification<ProductEntity> specification = Specification.where(ProductSpecs.byCategory(request.category()))
                        .and(ProductSpecs.byBrand(request.brand()))
                        .and(ProductSpecs.byPriceRange(request.priceRange()));
        return productRepository.findAll(specification, request.pageable()).map(ProductMapper::toDomain);
    }

    @Override
    public Optional<Product> byId(ProductId id) {
        return productRepository.findById(id.value()).map(ProductMapper::toDomain);
    }

    @Override
    public List<Product> byIdIn(List<ProductId> productIds) {
        final var ids = productIds.stream().map(ProductId::value).toList();
        return productRepository.findAllByIdIn(ids).stream().map(ProductMapper::toDomain).toList();
    }
}
