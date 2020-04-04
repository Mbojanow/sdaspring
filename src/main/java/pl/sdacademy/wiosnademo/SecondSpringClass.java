package pl.sdacademy.wiosnademo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import pl.sdacademy.OutsideOfContextExample;

@Component
public class SecondSpringClass implements CommandLineRunner {

  private final SdaLogger sdaLogger;
  private final OutsideOfContextExample outsideOfContextExample;

  public SecondSpringClass(/*@Qualifier("simpleLogger")*/ final SdaLogger sdaLogger,
                                                         final OutsideOfContextExample outsideOfContextExample) {
    this.sdaLogger = sdaLogger;
    this.outsideOfContextExample = outsideOfContextExample;
  }

  @Override
  public void run(final String... args) throws Exception {
    sdaLogger.logSomething();
  }
}
