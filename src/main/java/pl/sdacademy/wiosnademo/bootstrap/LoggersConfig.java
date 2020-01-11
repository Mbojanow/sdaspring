package pl.sdacademy.wiosnademo.bootstrap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggersConfig {

  @Bean
  public ILogger otherLogger() {
    return new OtherLogger();
  }
}
