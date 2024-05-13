package com.example.monolithtomicroservices.application.product;

import com.example.monolithtomicroservices.application.common.UseCase;
import com.example.monolithtomicroservices.application.exception.ResourceNotFoundException;
import com.example.monolithtomicroservices.domain.Product;
import com.example.monolithtomicroservices.domain.ProductId;
import org.springframework.stereotype.Service;

@Service
public class GetProductByIdUseCase implements UseCase<ProductId, Product> {
    private final GetProductByIdPort getProductByIdPort;

    public GetProductByIdUseCase(GetProductByIdPort getProductByIdPort) {
        this.getProductByIdPort = getProductByIdPort;
    }

    @Override
    public Product handle(ProductId request) {
        return getProductByIdPort.byId(request)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }
}
