package pl.sdacademy.wiosnademo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import pl.sdacademy.wiosnademo.producers.SimpleTextMessageProducer;

@Component
public class CLR implements CommandLineRunner {

  private final SimpleTextMessageProducer simpleTextMessageProducer;

  public CLR(final SimpleTextMessageProducer simpleTextMessageProducer) {
    this.simpleTextMessageProducer = simpleTextMessageProducer;
  }

  @Override
  public void run(final String... args) throws Exception {
    simpleTextMessageProducer.sendSimpleTextMessage("Hii FROM JAVAGDA32");
  }
}
