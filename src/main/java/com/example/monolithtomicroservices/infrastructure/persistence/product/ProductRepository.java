package com.example.monolithtomicroservices.infrastructure.persistence.product;

import com.example.monolithtomicroservices.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
