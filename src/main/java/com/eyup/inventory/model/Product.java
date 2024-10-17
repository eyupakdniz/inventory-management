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

    private Product(ProductBuilder builder) {
        this.id = builder.id;
        this.refCode = builder.refCode;
        this.name = builder.name;
        this.description = builder.description;
        this.quantity = builder.quantity;
        this.purchasePrice = builder.purchasePrice;
        this.active = builder.active;
        this.category = builder.category;
        this.productTemplate = builder.productTemplate;
    }


    public static class ProductBuilder {
        private Long id;
        private String refCode;
        private String name;
        private String description;
        private Integer quantity;
        private Double purchasePrice;
        private Boolean active;
        private Category category;
        private ProductTemplate productTemplate;

        public ProductBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public ProductBuilder setRefCode(String refCode) {
            this.refCode = refCode;
            return this;
        }

        public ProductBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public ProductBuilder setQuantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public ProductBuilder setPurchasePrice(Double purchasePrice) {
            this.purchasePrice = purchasePrice;
            return this;
        }

        public ProductBuilder setActive(Boolean active) {
            this.active = active;
            return this;
        }

        public ProductBuilder setCategory(Category category) {
            this.category = category;
            return this;
        }

        public ProductBuilder setProductTemplate(ProductTemplate productTemplate) {
            this.productTemplate = productTemplate;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
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