package pl.sdacademy.wiosnademo.names;

import org.springframework.stereotype.Component;

@Component("sayHiService")
public class HiService implements BaseType { //hiService

  @Override
  public void hello() {
    System.out.println("hi");
  }
}
