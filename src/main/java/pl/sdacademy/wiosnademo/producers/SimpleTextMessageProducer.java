package pl.sdacademy.wiosnademo.producers;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import pl.sdacademy.wiosnademo.model.SimpleTextMessage;

@Component
public class SimpleTextMessageProducer {

  private final JmsTemplate jmsTemplate;

  public SimpleTextMessageProducer(final JmsTemplate jmsTemplate) {
    this.jmsTemplate = jmsTemplate;
  }

  public void sendSimpleTextMessage(final String msg) {
    jmsTemplate.convertAndSend("simple_text_msg_queue", new SimpleTextMessage(msg),
        new PriorityMessagePostProcessor());
  }
}
