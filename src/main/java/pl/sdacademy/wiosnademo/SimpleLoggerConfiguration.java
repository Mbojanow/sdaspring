package pl.sdacademy.wiosnademo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class SimpleLoggerConfiguration {

  @Bean
  @Primary
  public SimpleLogger2 simpleLogger2() {
    return new SimpleLogger2();
  }
}
