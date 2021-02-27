package pl.sdacademy.wiosnademo.names;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class HelloService implements BaseType { // helloService

  @Override
  public void hello() {
    System.out.println("hello from HelloService");
  }
}
