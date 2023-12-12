package com.example.ecommerce.ecommerceservice.service;

import com.example.ecommerce.ecommerceservice.model.Product;
import com.example.ecommerce.ecommerceservice.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Objects.isNull;

@EnableCaching
@Component
@RequiredArgsConstructor
public class CachingService {

    public static final String PRODUCT_CACHE = "PRODUCT_CACHE";
    private final CacheManager cacheManager;
    private final ProductRepository productRepository;

    @PostConstruct
    public void intit(){
        loadProductsToCache();
    }

    public void loadProductsToCache(){
        List<Product> productList = productRepository.findAll();
        if(!productList.isEmpty()){
            for(Product product : productList){
                putToCache(PRODUCT_CACHE, product.getProductId(), product);
            }
        }
    }
    public void putToCache(String cacheName, String key, Product value) {
        Cache cache = cacheManager.getCache(cacheName);
        if (null != cache) {
            cache.put(key, value);
        }
    }

    public Product getFromCache(String cacheName, String key){
        Cache cache = cacheManager.getCache(cacheName);
        if(isNull(cache)) return null;
        return cache.get(key, Product.class);
    }

    public Product getProductData(String productId){

        Product product = getFromCache(PRODUCT_CACHE, productId);

        if(isNull(product)){
            product = productRepository.findById(productId).orElse(null);
            addProductDataToCache(product);
        }
        return product;
    }

    private void addProductDataToCache(Product product) {

        if(product != null){
            putToCache(PRODUCT_CACHE, product.getProductId(), product);
        }
    }

}
