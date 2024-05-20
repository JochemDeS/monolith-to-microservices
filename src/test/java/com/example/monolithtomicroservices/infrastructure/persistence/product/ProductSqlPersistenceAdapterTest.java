package com.example.monolithtomicroservices.infrastructure.persistence.product;

import com.example.monolithtomicroservices.application.product.ProductRequest;
import com.example.monolithtomicroservices.domain.Product;
import com.example.monolithtomicroservices.infrastructure.persistence.PersistenceTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.Assertions.assertThat;


@PersistenceTest
class ProductSqlPersistenceAdapterTest {
    @Autowired
    private ProductRepository productRepository;

    @ParameterizedTest
    @ValueSource(ints = {5, 1, 25})
    void shouldReturnDefaultNumberOfProducts(int size) {
        final var target = new ProductSqlPersistenceAdapter(productRepository);
        final var request = ProductRequest.builder()
                .pageable(PageRequest.of(0, size))
                .build();

        final var result = target.all(request);

        assertThat(result).isNotEmpty();
        assertThat(result.getContent()).hasSize(size);
        assertThat(result.getContent()).allMatch(Product.class::isInstance);
    }

}