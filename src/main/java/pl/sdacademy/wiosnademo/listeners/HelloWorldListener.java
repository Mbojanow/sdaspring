package pl.sdacademy.wiosnademo.listeners;

import javax.jms.Message;
import javax.jms.MessageListener;

public class HelloWorldListener implements MessageListener {

  @Override
  public void onMessage(final Message message) {
    System.out.println(message);
  }
}
