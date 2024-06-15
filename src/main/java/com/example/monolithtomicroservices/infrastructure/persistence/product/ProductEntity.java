package com.example.monolithtomicroservices.infrastructure.persistence.product;

import com.example.monolithtomicroservices.infrastructure.persistence.brand.BrandEntity;
import com.example.monolithtomicroservices.infrastructure.persistence.category.CategoryEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity(name = "Product")
@Table(name = "products")
public class ProductEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column @NotNull
    private String title;
    @Column @NotNull
    private String description;
    @Column @NotNull
    private double price;
    @Column @NotNull
    private int stock;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_id")
    private BrandEntity brand;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;
    @Column @NotNull
    private String thumbnail;
    @Column @NotNull
    private String image;

    public ProductEntity() {
    }

    private ProductEntity(final Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.description = builder.description;
        this.price = builder.price;
        this.stock = builder.stock;
        this.brand = builder.brand;
        this.category = builder.category;
        this.thumbnail = builder.thumbnail;
        this.image = builder.image;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public BrandEntity getBrand() {
        return brand;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getImage() {
        return image;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private long id;
        private String title;
        private String description;
        private double price;
        private int stock;
        private BrandEntity brand;
        private CategoryEntity category;
        private String thumbnail;
        private String image;

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder price(double price) {
            this.price = price;
            return this;
        }

        public Builder stock(int stock) {
            this.stock = stock;
            return this;
        }

        public Builder brand(BrandEntity brand) {
            this.brand = brand;
            return this;
        }

        public Builder category(CategoryEntity category) {
            this.category = category;
            return this;
        }

        public Builder thumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
            return this;
        }

        public Builder image(String image) {
            this.image = image;
            return this;
        }

        public ProductEntity build() {
            return new ProductEntity(this);
        }
    }
}
