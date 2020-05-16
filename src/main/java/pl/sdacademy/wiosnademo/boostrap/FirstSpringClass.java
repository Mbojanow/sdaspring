package pl.sdacademy.wiosnademo.boostrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FirstSpringClass implements CommandLineRunner {

  //@Autowired // inject do pola, wstrzykiwanie zależności
  private final SimpleLogger simpleLogger;
  private final SecondSpringClass secondSpringClass;

  // @Autowired // inject przez konstruktor - @AUtowired NIE JEST WYMAGANE przy injectowaniu przez konstruktor
  public FirstSpringClass(final SimpleLogger simpleLogger, final SecondSpringClass secondSpringClass) {
    this.simpleLogger = simpleLogger;
    this.secondSpringClass = secondSpringClass;
  }

  @Override
  public void run(final String... args) throws Exception {
    simpleLogger.logSomething();
    secondSpringClass.logSomething();
  }

//  @Autowired // inject przez setter
//  public void setSimpleLogger(final SimpleLogger simpleLogger) {
//    this.simpleLogger = simpleLogger;
//  }
}
