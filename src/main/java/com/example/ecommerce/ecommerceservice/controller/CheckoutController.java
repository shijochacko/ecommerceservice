package com.example.ecommerce.ecommerceservice.controller;

import com.example.ecommerce.ecommerceservice.dto.CheckOutResponse;
import com.example.ecommerce.ecommerceservice.service.CheckoutService;
import com.example.ecommerce.ecommerceservice.service.ValidationService;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.ROUND_HALF_UP;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CheckoutController {

    private final CheckoutService checkoutService;
    private final ValidationService validationService;

    @PostMapping(
            path = "/checkout",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CheckOutResponse> performCheckout(@RequestBody @NotEmpty(message = "Product Id's cannot be empty")
                                                            List<String> productIds) {
        productIds = validationService.validateAndFetchPricingProducts(productIds);

        double totalCost = checkoutService.calculateTotalCost(productIds);

        return ResponseEntity.ok(new CheckOutResponse(BigDecimal.valueOf(totalCost).setScale(2, ROUND_HALF_UP)));
    }
}
