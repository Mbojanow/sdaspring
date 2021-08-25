package com.example.validation_example;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import org.springframework.boot.CommandLineRunner;

import java.time.Duration;

//@Component
public class RateLimiterCLR implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        RateLimiterRegistry rateLimiterRegistry = RateLimiterRegistry.ofDefaults();

        RateLimiter rateLimiter = rateLimiterRegistry.rateLimiter("customRateLimiter",
                RateLimiterConfig.custom()
                        .limitRefreshPeriod(Duration.ofSeconds(3L))
                        .limitForPeriod(3)
                        .timeoutDuration(Duration.ofSeconds(2L))
                        .build());

        for (int i = 0; i < 10; i++) {
            rateLimiter.executeRunnable(() -> {
                System.out.println("Hello");
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
