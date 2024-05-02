package com.example.monolithtomicroservices.infrastructure.persistence.brand;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity(name = "Brand")
@Table(name = "brands")
public class BrandEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column @NotNull
    private String name;

    public BrandEntity() {
    }

    private BrandEntity(final Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private long id;
        private String name;

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public BrandEntity build() {
            return new BrandEntity(this);
        }
    }
}
