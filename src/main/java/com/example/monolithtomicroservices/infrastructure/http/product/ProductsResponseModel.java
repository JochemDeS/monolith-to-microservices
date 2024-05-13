package com.example.monolithtomicroservices.infrastructure.http.product;

import java.util.List;

public record ProductsResponseModel(
        List<ProductReadModel> products,
        int page,
        int size,
        int totalProducts
) {
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<ProductReadModel> products;
        private int page;
        private int size;
        private int totalProducts;

        public Builder products(List<ProductReadModel> products) {
            this.products = products;
            return this;
        }

        public Builder page(int page) {
            this.page = page;
            return this;
        }

        public Builder size(int size) {
            this.size = size;
            return this;
        }

        public Builder totalProducts(int totalProducts) {
            this.totalProducts = totalProducts;
            return this;
        }

        public ProductsResponseModel build() {
            return new ProductsResponseModel(products, page, size, totalProducts);
        }
    }
}
