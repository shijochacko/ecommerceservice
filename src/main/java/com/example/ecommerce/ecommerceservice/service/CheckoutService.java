package com.example.ecommerce.ecommerceservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Service
public class CheckoutService {

    private static final Logger LOG = LoggerFactory.getLogger(CheckoutService.class);

    private final PriceEngineService priceEngineService;

    @Autowired
    public CheckoutService(PriceEngineService priceEngineService) {
        this.priceEngineService = priceEngineService;
    }

    public double calculateTotalCost(List<String> pricingProducts) {

        Map<String, Long> pricingProductMap = groupCheckoutProducts(pricingProducts);
        return priceEngineService.calculateTotalPrice(pricingProductMap);

    }

    private static Map<String, Long> groupCheckoutProducts(List<String> checkedOutProducts) {
        return checkedOutProducts.stream()
                .collect(groupingBy(Function.identity(), counting()));
    }
}

