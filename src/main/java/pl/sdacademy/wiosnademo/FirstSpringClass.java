package pl.sdacademy.wiosnademo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FirstSpringClass implements CommandLineRunner {

  private final SdaLogger sdaLogger;

  public FirstSpringClass(/*@Qualifier("simpleLogger")*/ final SdaLogger sdaLogger) {
    this.sdaLogger = sdaLogger;
  }

  @Override
  public void run(final String... args) throws Exception {
    sdaLogger.logSomething();
  }


  // wstrzykniecie, inject zależności do pola
  //@Autowired
//  private SimpleLogger simpleLogger;
//  private SimpleLogger2 simpleLogger2;
//
//  //@Autowired od 4.2
//  public FirstSpringClass(final SimpleLogger simpleLogger, final SimpleLogger2 simpleLogger2) {
//    this.simpleLogger = simpleLogger;
//    this.simpleLogger2 = simpleLogger2;
//  }
//
//  @Override
//  public void run(final String... args) throws Exception {
//    simpleLogger.logSomething();
//    simpleLogger2.logSomething();
//  }

//  @Autowired
//  public void setSimpleLogger(final SimpleLogger simpleLogger) {
//    this.simpleLogger = simpleLogger;
//  }
}
