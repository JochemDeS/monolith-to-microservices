package com.example.monolithtomicroservices.infrastructure.persistence.product;

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
    private double discountPercentage;
    @Column @NotNull
    private double rating;
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
}
