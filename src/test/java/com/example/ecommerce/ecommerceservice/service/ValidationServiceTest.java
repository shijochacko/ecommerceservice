package com.example.ecommerce.ecommerceservice.service;

import com.example.ecommerce.ecommerceservice.exception.ApiException;
import com.example.ecommerce.ecommerceservice.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.EMPTY_LIST;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ValidationServiceTest {

    public static final String PRODUCT_CACHE = "PRODUCT_CACHE";

    public static final String NO_PRODUCT_DATA_FOUND = "No product data found";
    public static final String INVALID_INPUT = "Invalid Input";

    private ValidationService validationService;

    private CachingService cachingService;

    @BeforeEach
    public void setup() {
        cachingService = mock(CachingService.class);
        loadCacheData();
        validationService = new ValidationService(cachingService);
    }

    @Test
    void validateAndFetchPricingProducts_shouldReturnProductIdList() {

        List<String> productList = List.of("0005");

        List<String>  products = validationService.validateAndFetchPricingProducts(productList);

        assertTrue(products.contains("0005"));
    }

    @Test
    void validateAndFetchPricingProducts_shouldThrowExceptionForInvalidProductId() {

        List<String> productList = List.of("0011", "0012", "0013", "0014");

        ApiException checkoutServiceException =   assertThrows(ApiException.class, () ->
                validationService.validateAndFetchPricingProducts(productList));

        String expectedErrorMessage = "No matching data found for product [0011, 0012, 0013, 0014]";


        System.out.println(checkoutServiceException.getErrorMessage());
        assertEquals(checkoutServiceException.getErrorMessage(), expectedErrorMessage);
    }

    @Test
    void validateCheckedOutProducts_shouldThrowExceptionForNullInput() {

        ApiException checkoutServiceException =   assertThrows(ApiException.class, () ->
                validationService.validateAndFetchPricingProducts(null));

        assertEquals(checkoutServiceException.getErrorMessage(), NO_PRODUCT_DATA_FOUND);
    }

    @Test
    void validateCheckedOutProducts_shouldThrowExceptionForEmptyInput() {

        ApiException checkoutServiceException =   assertThrows(ApiException.class, () ->
                validationService.validateAndFetchPricingProducts(EMPTY_LIST));

        assertEquals(checkoutServiceException.getErrorMessage(), NO_PRODUCT_DATA_FOUND);
    }

    @Test
    void validateCheckedOutProducts_shouldThrowExceptionForProductListWithNoInput() {

        ApiException checkoutServiceException =   assertThrows(ApiException.class, () ->
                validationService.validateAndFetchPricingProducts(new ArrayList<>()));

        assertEquals(checkoutServiceException.getErrorMessage(), NO_PRODUCT_DATA_FOUND);
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
        when(cachingService.getProductData("0005")).thenReturn(productMap.get("0005"));
        when(cachingService.getProductData("0006")).thenReturn(productMap.get("0006"));
        when(cachingService.getProductData("0007")).thenReturn(productMap.get("0007"));
        when(cachingService.getProductData("0008")).thenReturn(productMap.get("0008"));
    }

}
