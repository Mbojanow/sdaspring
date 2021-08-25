package com.example.validation_example;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SomeClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @CircuitBreaker(name = "backendA")
    public void callExternalService() {
        System.out.println("Calling external service");
        restTemplate.getForEntity("http://localhost:8081", Void.class);
    }
}
