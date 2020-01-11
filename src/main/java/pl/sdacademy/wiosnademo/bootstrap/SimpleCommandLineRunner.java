package pl.sdacademy.wiosnademo.bootstrap;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SimpleCommandLineRunner implements CommandLineRunner {

  private final ILogger iLogger;

  // @Named - odpowiednik @Qualifier
  public SimpleCommandLineRunner(/*@Qualifier("otherLogger")*/ final ILogger iLogger) {
    this.iLogger = iLogger;
  }

  @Override
  public void run(final String... args) throws Exception {
    iLogger.logSomeStuff();
  }


  //@Autowired - przez pole - niezalecane
  // @Inject -javaEE, @Resource
//  private final SimpleLogger simpleLogger;
//  private final OtherLogger otherLogger;
//
//  //@Autowired - niewymagane
//  public SimpleCommandLineRunner(final SimpleLogger simpleLogger, final OtherLogger otherLogger) {
//    this.simpleLogger = simpleLogger;
//    this.otherLogger = otherLogger;
//  }
//
//  @Override
//  public void run(final String... args) throws Exception {
//    simpleLogger.logSomeStuff();
//    otherLogger.logSomeStuff();
//  }

  //@Autowired - inject przez setter - autowired wymagane
//  public void setSimpleLogger(final SimpleLogger simpleLogger) {
//    this.simpleLogger = simpleLogger;
//  }
}
