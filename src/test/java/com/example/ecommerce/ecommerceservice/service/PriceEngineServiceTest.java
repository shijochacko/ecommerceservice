package com.example.ecommerce.ecommerceservice.service;

import com.example.ecommerce.ecommerceservice.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PriceEngineServiceTest {

    public static final String PRODUCT_CACHE = "PRODUCT_CACHE";

    private PriceEngineService priceEngineService;
    private CachingService cachingService;

    @BeforeEach
    public void setup() {
        cachingService = mock(CachingService.class);
        loadCacheData();
        priceEngineService = new PriceEngineService(cachingService);
    }

    @Test
    void calculateTotalCost_shouldReturnTotalCostForSingleProduct() {

        List<String> productList = List.of("0005");

        double result = priceEngineService.calculateTotalPrice(groupCheckoutProducts(productList));

        assertEquals(1000.00, result);
    }
    @Test
    void calculateTotalCost_shouldReturnTotalCostForMultipleProduct() {

        List<String> productList = List.of("0005", "0006", "0005", "0008");

        double result = priceEngineService.calculateTotalPrice(groupCheckoutProducts(productList));

        assertEquals(2110.00, result);
    }

    @Test
    void calculateTotalCost_shouldReturnTotalCostForProductWithOutDiscount() {

        List<String> productList = List.of("0007");

        double result = priceEngineService.calculateTotalPrice(groupCheckoutProducts(productList));

        assertEquals(400, result);

    }

    @Test
    void calculateTotalCost_shouldReturnCorrectTotalCostForMulitpleSameProductWithAndWithOutDiscount() {

        List<String> productList = List.of("0005","0005","0005","0005");

        double result = priceEngineService.calculateTotalPrice(groupCheckoutProducts(productList));

        assertEquals(3000, result);
    }
    @Test
    void calculateTotalCost_shouldReturnTotalCostForMultipleProductWithOutDiscount() {

        List<String> productList = List.of("0007","0007","0007");

        double result = priceEngineService.calculateTotalPrice(groupCheckoutProducts(productList));

        assertEquals(1200, result);
    }

    @Test
    void calculateTotalCost_shouldReturnTotalCostForCombinationOfProducts() {

        List<String> productList = List.of("0005", "0005", "0005","0005", "0005", "0005", "0006","0006","0007","0008");

        double result = priceEngineService.calculateTotalPrice(groupCheckoutProducts(productList));

        assertEquals(4550, result);
    }
    static Map<String, Long> groupCheckoutProducts(List<String> checkedOutProducts) {
        return checkedOutProducts.stream()
                .collect(groupingBy(Function.identity(), counting()));
    }
    public Map<String, Product> loadTestDataMap() {
        Map<String, Product> productMap = new HashMap<>();
        productMap.put("0005", new Product("0005", "rolex watch", 1000.00, 3, 2000.00));
        productMap.put("0006", new Product("0006", "Michael Kors Purse",80.00, 2, 120.00));
        productMap.put("0007", new Product("0007", "iPhone XS",400.00, null, null));
        productMap.put("0008", new Product("0008", "Casio Watch",30.00, null, null));
        return productMap;
    }
    private void loadCacheData() {
        Map<String, Product> productMap = loadTestDataMap();
        when(cachingService.getFromCache(PRODUCT_CACHE, "0005")).thenReturn(productMap.get("0005"));
        when(cachingService.getFromCache(PRODUCT_CACHE, "0006")).thenReturn(productMap.get("0006"));
        when(cachingService.getFromCache(PRODUCT_CACHE, "0007")).thenReturn(productMap.get("0007"));
        when(cachingService.getFromCache(PRODUCT_CACHE, "0008")).thenReturn(productMap.get("0008"));
    }
}
