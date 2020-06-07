package pl.sdacademy.wiosnademo.listeners;

import static pl.sdacademy.wiosnademo.config.Queues.HELLO_WORLD;

import javax.jms.Message;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import lombok.SneakyThrows;

@Component
public class DeclarativeHelloWorldListener {

  @SneakyThrows
  @JmsListener(destination = HELLO_WORLD)
  public void onHelloWorldMessage(final Message message) {
    System.out.println("HELLO WORLD!!!");
    System.out.println(message.getBody(String.class));
  }
}
