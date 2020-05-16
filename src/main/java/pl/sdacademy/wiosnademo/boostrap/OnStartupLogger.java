package pl.sdacademy.wiosnademo.boostrap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class OnStartupLogger implements CommandLineRunner {

  private final DummyLogger dummyLogger;

//  @Autowired
//  private List<DummyLogger> dummyLoggerList;

  public OnStartupLogger(@Qualifier("simpleLogger") final DummyLogger dummyLogger) {
    this.dummyLogger = dummyLogger;
  }

  @Override
  public void run(final String... args) throws Exception {
    dummyLogger.logSomething();
  }
}
