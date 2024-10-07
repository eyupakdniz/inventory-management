package com.eyup.inventory.model;

import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String refCode;
    private String name;
    private String description;
    private Integer quantity;
    private Double purchasePrice;
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "template_id")
    private ProductTemplate productTemplate;

    public Product(String name, String refCode, String description, Integer quantity, Double purchasePrice, Category category, ProductTemplate productTemplate) {
        this.name = name;
        this.refCode = refCode;
        this.description = description;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
        this.category = category;
        this.productTemplate = productTemplate;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ProductTemplate getProductTemplate() {
        return productTemplate;
    }

    public void setProductTemplate(ProductTemplate productTemplate) {
        this.productTemplate = productTemplate;
    }

    public String getRefCode() {
        return refCode;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode;
    }
}