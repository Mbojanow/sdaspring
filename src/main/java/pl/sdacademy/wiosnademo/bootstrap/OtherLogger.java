package pl.sdacademy.wiosnademo.bootstrap;

import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OtherLogger implements ILogger {

  @Override
  public void logSomeStuff() {
    log.info("Hello from OTHER LOGGER");
  }
}
