package com.example.ecommerce.ecommerceservice.controller;


import com.example.ecommerce.ecommerceservice.EcommerceserviceApplication;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MySQLContainer;

@SpringBootTest(classes = EcommerceserviceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CheckoutControllerContainerTest {
    @Autowired
    public MockMvc mockMvc;

    @ServiceConnection
    private static final MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.2");

    static {
        mySQLContainer.withUrlParam("serverTimezone", "UTC")
                .withReuse(true)
                .start();
    }

    @BeforeAll
    public void beforeAll(){
        mySQLContainer.start();
    }
    @AfterAll
    public void afterAll(){
        mySQLContainer.stop();
    }
}
