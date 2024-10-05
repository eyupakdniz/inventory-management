package com.eyup.inventory.dto;

import com.eyup.inventory.model.Product;

public class ProductViewDto {

    private String refCode;
    private String name;
    private String description;
    private Integer quantity;
    private Double purchasePrice;

    private Long categoryId;
    private Long productTemplateId;

    public static ProductViewDto of(Product product) {
        ProductViewDto productViewDto = new ProductViewDto();
        productViewDto.setName(product.getName());
        productViewDto.setRefCode(product.getRefCode());
        productViewDto.setDescription(product.getDescription());
        productViewDto.setQuantity(product.getQuantity());
        productViewDto.setPurchasePrice(product.getPurchasePrice());
        productViewDto.setCategoryId(product.getCategory().getId());
        productViewDto.setProductTemplateId(product.getProductTemplate().getId());
        return productViewDto;
    }

    public String getRefCode() {
        return refCode;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode;
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
}
