package pl.sdacademy.wiosnademo.producers;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import pl.sdacademy.wiosnademo.config.Queues;
import pl.sdacademy.wiosnademo.model.PartsStockDto;

@Component
public class PartsStockProducer {

  private final JmsTemplate jmsTemplate;

  public PartsStockProducer(final JmsTemplate jmsTemplate) {
    this.jmsTemplate = jmsTemplate;
  }

  public void addToStock(final String name, final Long stock) throws JMSException {
    final TextMessage message = (TextMessage)jmsTemplate.sendAndReceive(Queues.PARTS_STOCK, session -> {
      final ObjectMessage objectMessage = session.createObjectMessage(new PartsStockDto(name, stock));
      return objectMessage;
    });
    System.out.println(message.getText());
  }
}
