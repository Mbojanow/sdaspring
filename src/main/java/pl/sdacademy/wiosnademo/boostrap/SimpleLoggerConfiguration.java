package pl.sdacademy.wiosnademo.boostrap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class SimpleLoggerConfiguration {

  @Primary
  @Bean // @Configuration + @Bean -> zwracany obiekt trafi do kontekstu/IoC/worka zależności
  public SecondSpringClass secondSpringClass() {
    return new SecondSpringClass();
  }
}
