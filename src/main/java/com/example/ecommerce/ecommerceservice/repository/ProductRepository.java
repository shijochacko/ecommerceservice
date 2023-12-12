package com.example.ecommerce.ecommerceservice.repository;

import com.example.ecommerce.ecommerceservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
