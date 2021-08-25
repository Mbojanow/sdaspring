package com.example.validation_example;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.boot.CommandLineRunner;

import java.time.Duration;

//@Component
public class CBCLR implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.ofDefaults();

        CircuitBreaker webinarCB = circuitBreakerRegistry.circuitBreaker("webinarCB",
                CircuitBreakerConfig.custom()
                        .failureRateThreshold(20)
                        .slidingWindow(10, 5, CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                        .automaticTransitionFromOpenToHalfOpenEnabled(true)
                        .permittedNumberOfCallsInHalfOpenState(2)
                        .waitDurationInOpenState(Duration.ofSeconds(2L))
                        .build());

        System.out.println(webinarCB.getState());
        for (int i = 0; i < 10; i++) {
            try {
                webinarCB.decorateRunnable(() -> runDemo()).run();
            } catch (RuntimeException exp) {

            }
        }

        System.out.println(webinarCB.getState());
        Thread.sleep(1000L);
        System.out.println(webinarCB.getState());
        Thread.sleep(1000L);
        System.out.println(webinarCB.getState());
        Thread.sleep(1000L);
        try {
            webinarCB.decorateRunnable(() -> runDemo()).run();
        } catch (RuntimeException exp) {

        }
        try {
            webinarCB.decorateRunnable(() -> runDemo()).run();
        } catch (RuntimeException exp) {

        }
        try {
            webinarCB.decorateRunnable(() -> runDemo()).run();
        } catch (RuntimeException exp) {

        }
        System.out.println(webinarCB.getState());
        Thread.sleep(1000L);
    }

    private static int index = 0;

    private void runDemo() {
        System.out.println("hello");
        if (index>=5) {
            return;
        }
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        index++;
        throw new IllegalStateException();
    }
}
