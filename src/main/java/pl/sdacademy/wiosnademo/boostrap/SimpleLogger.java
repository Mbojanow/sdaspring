package pl.sdacademy.wiosnademo.boostrap;

import org.springframework.stereotype.Component;

@Component
public class SimpleLogger implements DummyLogger {

  @Override
  public void logSomething() {
    System.out.println("Hello World Spring");
  }
}
