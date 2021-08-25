package com.example.validation_example;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnotherCLR implements CommandLineRunner {

    private final SomeClient someClient;


    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 10; i++) {
            try {
                someClient.callExternalService();
            } catch (Exception e) {
                System.out.println("x");
            }
        }

    }
}
