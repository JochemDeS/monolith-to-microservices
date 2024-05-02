package com.example.monolithtomicroservices.application.product;

import com.example.monolithtomicroservices.application.common.ReturnUseCase;
import com.example.monolithtomicroservices.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllProductsUseCase implements ReturnUseCase<List<Product>> {
    private final GetAllProductsPort getAllProductsPort;

    public GetAllProductsUseCase(GetAllProductsPort getAllProductsPort) {
        this.getAllProductsPort = getAllProductsPort;
    }

    @Override
    public List<Product> handle() {
        return getAllProductsPort.all();
    }
}
