package pl.sdacademy.wiosnademo.bootstrap;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Primary
@Component
@Slf4j
public class SimpleLogger implements ILogger {

  @Override
  public void logSomeStuff() {
    log.info("Hello from Simple Command Line Runner");
  }
}
