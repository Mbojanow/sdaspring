package pl.sdacademy.wiosnademo.names;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class OnStartup implements CommandLineRunner {

  private final BaseType baseType;

  public OnStartup(@Qualifier("sayHiService") final BaseType baseType) {
    this.baseType = baseType;
  }

  @Override
  public void run(final String... args) throws Exception {
    baseType.hello();
  }
}
