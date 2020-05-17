package pl.sdacademy.wiosnademo.boostrap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
public class SimpleLogger implements DummyLogger {

  @Override
  public void logSomething() {
    System.out.println("Hello World Spring");
  }
}
