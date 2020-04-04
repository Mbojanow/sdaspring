package pl.sdacademy.wiosnademo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

@Configuration
public class SimpleLoggerConfiguration {

  @Bean
  @Primary
  @Scope("prototype")
  public SimpleLogger2 simpleLogger2() {
    return new SimpleLogger2();
  }
}
