package com.example.monolithtomicroservices.infrastructure.persistence.brand;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<BrandEntity, Long> {
}
