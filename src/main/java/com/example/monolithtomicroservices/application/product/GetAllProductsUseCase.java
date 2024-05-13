package com.example.monolithtomicroservices.application.product;

import com.example.monolithtomicroservices.application.common.UseCase;
import com.example.monolithtomicroservices.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllProductsUseCase implements UseCase<Pageable, Page<Product>> {
    private final GetAllProductsPort getAllProductsPort;

    public GetAllProductsUseCase(GetAllProductsPort getAllProductsPort) {
        this.getAllProductsPort = getAllProductsPort;
    }

    @Override
    public Page<Product> handle(Pageable pageable) {
        return getAllProductsPort.all(pageable);
    }
}
