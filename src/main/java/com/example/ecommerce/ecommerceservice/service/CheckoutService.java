package com.example.ecommerce.ecommerceservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RequiredArgsConstructor
public class CheckoutService {

    private final PriceEngineService priceEngineService;
    public double calculateTotalCost(List<String> pricingProducts) {
        log.info("Inside Calculate Total Cost");
        Map<String, Long> pricingProductMap = groupCheckoutProducts(pricingProducts);
        return priceEngineService.calculateTotalPrice(pricingProductMap);

    }

    private static Map<String, Long> groupCheckoutProducts(List<String> checkedOutProducts) {
        return checkedOutProducts.stream()
                .collect(groupingBy(Function.identity(), counting()));
    }
}

