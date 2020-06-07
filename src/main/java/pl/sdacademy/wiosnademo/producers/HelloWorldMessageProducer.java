package pl.sdacademy.wiosnademo.producers;

import static pl.sdacademy.wiosnademo.config.Queues.HELLO_WORLD;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldMessageProducer {

  private final JmsTemplate jmsTemplate;

  public HelloWorldMessageProducer(final JmsTemplate jmsTemplate) {
    this.jmsTemplate = jmsTemplate;
  }

  public void sendHelloWorld() {
    jmsTemplate.send(HELLO_WORLD, new MessageCreator() {
      @Override
      public Message createMessage(final Session session) throws JMSException {
        return session.createTextMessage("Hello world from JMS!");
      }
    });
  }
}
