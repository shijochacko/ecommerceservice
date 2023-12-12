package com.example.ecommerce.ecommerceservice.service;

import com.example.ecommerce.ecommerceservice.model.Product;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PriceEngineService {
    private final CachingService cachingService;
    public static final String PRODUCT_CACHE = "PRODUCT_CACHE";

    public PriceEngineService(CachingService cachingService) {
        this.cachingService = cachingService;
    }

    public double calculateTotalPrice(Map<String, Long> checkedOutProductMap) {

        double totalPrice = 0.00;

        for (String productId : checkedOutProductMap.keySet()) {
            Product product = cachingService.getFromCache(PRODUCT_CACHE, productId);
            if (product != null) {
                totalPrice += calculatePrice(product, checkedOutProductMap.get(productId));
            }
        }
        return totalPrice;
    }
    private double calculatePrice(Product product, Long count) {

        double price = 0.00;

        if (null != product.getDiscountedPrice() &&
                null != product.getDiscountEligibleQuantity() &&
                count >= product.getDiscountEligibleQuantity()) {

            price = (count / product.getDiscountEligibleQuantity()) * product.getDiscountedPrice() +
                    (count % product.getDiscountEligibleQuantity()) * product.getUnitProce();
        } else {
            price += count * product.getUnitProce();
        }
        return price;
    }
}
