package com.example.monolithtomicroservices.infrastructure.persistence.brand;

import com.example.monolithtomicroservices.infrastructure.persistence.product.ProductEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Brand")
@Table(name = "brands")
public class BrandEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column @NotNull
    private String name;

    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProductEntity> products;

    public BrandEntity() {
    }

    private BrandEntity(final Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.products = builder.products;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private long id;
        private String name;
        private List<ProductEntity> products = new ArrayList<>();

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder products(List<ProductEntity> products) {
            this.products = products;
            return this;
        }

        public BrandEntity build() {
            return new BrandEntity(this);
        }
    }
}
