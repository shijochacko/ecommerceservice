package com.example.ecommerce.ecommerceservice.model;

import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @Column(name="product_id")
    private String productId;
    @Column(name="product_name")
    private String productName;
    @Column(name="unit_proce")
    private Double unitProce;
    @Column(name="discount_eligible_quantity")
    private Integer discountEligibleQuantity;
    @Column(name="discounted_price")
    private Double discountedPrice;



    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Double getUnitProce() {
        return unitProce;
    }

    public Integer getDiscountEligibleQuantity() {
        return discountEligibleQuantity;
    }

    public Double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setUnitProce(Double unitProce) {
        this.unitProce = unitProce;
    }

    public void setDiscountEligibleQuantity(Integer discountEligibleQuantity) {
        this.discountEligibleQuantity = discountEligibleQuantity;
    }

    public void setDiscountedPrice(Double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public Product() {}
    public Product(String productId, String productName, Double unitProce, Integer discountEligibleQuantity, Double discountedPrice) {
        this.productId = productId;
        this.productName = productName;
        this.unitProce = unitProce;
        this.discountEligibleQuantity = discountEligibleQuantity;
        this.discountedPrice = discountedPrice;
    }

}
