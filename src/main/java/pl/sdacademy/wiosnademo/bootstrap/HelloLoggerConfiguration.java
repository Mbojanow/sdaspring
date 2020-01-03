package pl.sdacademy.wiosnademo.bootstrap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloLoggerConfiguration {

    @Bean
    public HelloLogger2 helloLogger2() {
        return new HelloLogger2();
    }
}
