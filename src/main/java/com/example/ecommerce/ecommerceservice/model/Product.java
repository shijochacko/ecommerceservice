package com.example.ecommerce.ecommerceservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
}
