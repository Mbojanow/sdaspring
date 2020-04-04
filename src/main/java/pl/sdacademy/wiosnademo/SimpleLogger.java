package pl.sdacademy.wiosnademo;

import org.springframework.stereotype.Component;

@Component
public class SimpleLogger implements SdaLogger {

  public void logSomething() {
    System.out.println("Hello from simple logger");
  }
}
