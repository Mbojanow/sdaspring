package pl.sdacademy.wiosnademo.listeners;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import pl.sdacademy.wiosnademo.model.SimpleTextMessage;

@Component
public class SimpleTextMessageListener {

  @JmsListener(destination = "simple_text_msg_queue", selector = "javagroupid = '32'")
  public void handleMessage(@Payload final SimpleTextMessage message,
                            @Header("jms_priority") final String priority,
                            @Header("javagroupid") final String groupId) {
    System.out.println(message.getMsg());
    System.out.println(priority + " " + groupId);
  }
}
