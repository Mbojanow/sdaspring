package pl.sdacademy.wiosnademo.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SimpleCommandLineRunner2 implements CommandLineRunner {

  private final ILogger iLogger;

  // @Named - odpowiednik @Qualifier
  public SimpleCommandLineRunner2(/*@Qualifier("otherLogger")*/ final ILogger iLogger) {
    this.iLogger = iLogger;
  }

  @Override
  public void run(final String... args) throws Exception {
    iLogger.logSomeStuff();
  }}
