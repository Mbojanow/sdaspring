package com.example.validation_example;

import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.bulkhead.BulkheadConfig;
import io.github.resilience4j.bulkhead.BulkheadRegistry;
import org.springframework.boot.CommandLineRunner;

import java.time.Duration;

//@Component
public class BulkheadCLR implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        BulkheadRegistry bulkheadRegistry = BulkheadRegistry.ofDefaults();

        Bulkhead customBH = bulkheadRegistry.bulkhead("customBH",
                BulkheadConfig.custom()
                        .maxConcurrentCalls(3)
                        .maxWaitDuration(Duration.ofSeconds(0L))
                        .build());

        Runnable runnable = () -> {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("x");
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("y");
        };

        Runnable decorateRunnable = Bulkhead.decorateRunnable(customBH, runnable);

        for (int i = 0; i < 4; i++) {
            //customBH.acquirePermission();
            Thread thread = new Thread(decorateRunnable);
            thread.start();
        }
    }
}

