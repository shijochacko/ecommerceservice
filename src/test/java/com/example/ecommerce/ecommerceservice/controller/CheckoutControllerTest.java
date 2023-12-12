package com.example.ecommerce.ecommerceservice.controller;

import com.example.ecommerce.ecommerceservice.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CheckoutControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private  ProductRepository productRepository;

    @Test
    void performCheckout_shouldGetSuccessResponse() throws Exception {

        String input = new ObjectMapper().writeValueAsString(List.of("0005","0005","0005"));
        mockMvc.perform(post("/api/v1/checkout")
                        .contentType(MediaType.APPLICATION_JSON).content(input))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.price").value(2000));

    }
    @Test
    void performCheckout_shouldVeifyUnAuthorizedRequestError() throws Exception {

        String input = new ObjectMapper().writeValueAsString(List.of("0005", "0006", "0007"));
        mockMvc.perform(post("/api/v1/getTotal")
                        .contentType(MediaType.APPLICATION_JSON).content(input))
                .andExpect(status().isForbidden());

    }

    @Test
    void performCheckout_shouldThrow4xxErrorResponse() throws Exception {

        String input = new ObjectMapper().writeValueAsString(List.of());
        mockMvc.perform(post("/api/v1/checkout")
                        .contentType(MediaType.APPLICATION_JSON).content(input))
                .andExpect(status().is4xxClientError());

    }
}
