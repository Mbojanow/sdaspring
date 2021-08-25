package com.example.validation_example;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.boot.CommandLineRunner;

import java.time.Duration;

//@Component
public class RetryCLR implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        RetryRegistry registry = RetryRegistry.ofDefaults();
        Retry simpleRetry = registry.retry("simpleRetry", RetryConfig.custom()
                .maxAttempts(3)
                .waitDuration(Duration.ofSeconds(1L))
                .build());

        Runnable decoratedRunnable = Retry.decorateRunnable(simpleRetry, () -> {
            System.out.println("x");
            throw new RuntimeException();
        });

        decoratedRunnable.run();

    }
}

