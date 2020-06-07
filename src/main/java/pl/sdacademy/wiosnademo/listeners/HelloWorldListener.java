package pl.sdacademy.wiosnademo.listeners;

import javax.jms.Message;
import javax.jms.MessageListener;

import lombok.SneakyThrows;

public class HelloWorldListener implements MessageListener {

  @SneakyThrows
  @Override
  public void onMessage(final Message message) {
    System.out.println(message.getBody(String.class));
  }
}
