package com.example.ecommerce.ecommerceservice.service;

import com.example.ecommerce.ecommerceservice.exception.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Service
public class ValidationService {
    private static final Logger LOG = LoggerFactory.getLogger(ValidationService.class);
    public static final String NO_PRODUCT_DATA_FOUND = "No product data found";
    public static final String NO_MATCHING_DATA_FOUND_FOR_PRODUCT = "No matching data found for product";
    public static final String INVALID_INPUT = "Invalid Input";

    private final CachingService cachingService;


    public ValidationService(CachingService cachingService) {
        this.cachingService = cachingService;
    }
    public List<String> validateAndFetchPricingProducts(List<String> checkedOutProducts){

        List<String> missingProducts = new ArrayList<>();
        List<String> pricingProducts = new ArrayList<>();

        if(CollectionUtils.isEmpty(checkedOutProducts)){
            throw new ApiException(NO_PRODUCT_DATA_FOUND);
        }

        for(String productId : checkedOutProducts){
            if(isNull(cachingService.getProductData(productId))) {
                missingProducts.add(productId);
            }
            pricingProducts.add(productId);
        }
        if(missingProducts.size() == checkedOutProducts.size()){
            throwValidationException(missingProducts);
        }
     return pricingProducts;
    }

    private void throwValidationException(List<String> missingProducts) {

        LOG.error(NO_MATCHING_DATA_FOUND_FOR_PRODUCT + " {}", missingProducts);

        throw new ApiException(INVALID_INPUT,
                String.format(NO_MATCHING_DATA_FOUND_FOR_PRODUCT+" %s", missingProducts));
    }

}
