package pl.sdacademy.wiosnademo.producers;

import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.jms.core.MessagePostProcessor;


public class PriorityMessagePostProcessor implements MessagePostProcessor {

  @Override
  public Message postProcessMessage(final Message message) throws JMSException {
    message.setJMSPriority(5);
    message.setStringProperty("javagroupid", "32");
    return message;
  }
}
