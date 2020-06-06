package pl.sdacademy.wiosnademo.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SimpleCML implements CommandLineRunner {
  @Override
  public void run(final String... args) throws Exception {
    System.out.println("Beans: " + BeanCounter.beanCount);
  }
}
