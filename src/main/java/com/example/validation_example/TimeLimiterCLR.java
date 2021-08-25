package com.example.validation_example;

import io.github.resilience4j.timelimiter.TimeLimiter;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import io.github.resilience4j.timelimiter.TimeLimiterRegistry;
import org.springframework.boot.CommandLineRunner;

import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

//@Component
public class TimeLimiterCLR implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        TimeLimiterRegistry timeLimiterRegistry = TimeLimiterRegistry.ofDefaults();

        TimeLimiter tl = timeLimiterRegistry.timeLimiter("tl", TimeLimiterConfig.custom()
                .cancelRunningFuture(true)
                .timeoutDuration(Duration.ofSeconds(5L))
                .build());

        Callable<Void> x = tl.decorateFutureSupplier(() -> CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("x");
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("y");
        }));

        x.call();

    }
}
