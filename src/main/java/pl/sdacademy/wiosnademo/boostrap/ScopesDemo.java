package pl.sdacademy.wiosnademo.boostrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ScopesDemo implements CommandLineRunner {
  private final SimpleLogger simpleLogger;
  private final SecondSpringClass secondSpringClass;

  public ScopesDemo(final SimpleLogger simpleLogger, final SecondSpringClass secondSpringClass) {
    this.simpleLogger = simpleLogger;
    this.secondSpringClass = secondSpringClass;
  }

  @Override
  public void run(final String... args) throws Exception {
    simpleLogger.logSomething();
    secondSpringClass.logSomething();
  }
}
