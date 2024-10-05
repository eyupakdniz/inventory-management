package com.eyup.inventory.dto;

import com.eyup.inventory.model.Category;
import com.eyup.inventory.model.ProductTemplate;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class ProductUpdateDto {

    private String name;
    private String description;
    private String refCode;
    private Integer quantity;
    private Double purchasePrice;
    private Boolean active;

    private Long categoryId;
    private Long productTemplateId;

    // Constructor
    public ProductUpdateDto() {
    }

    // Getters and Setters
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getProductTemplateId() {
        return productTemplateId;
    }

    public void setProductTemplateId(Long productTemplateId) {
        this.productTemplateId = productTemplateId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getRefCode() {
        return refCode;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode;
    }
}
