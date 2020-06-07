package pl.sdacademy.wiosnademo.producers;

import static pl.sdacademy.wiosnademo.config.Queues.MANUFACTURERS;

import javax.jms.MapMessage;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class ManufacturerMessageProducer {

  private final JmsTemplate jmsTemplate;

  public ManufacturerMessageProducer(final JmsTemplate jmsTemplate) {
    this.jmsTemplate = jmsTemplate;
  }

  public void sentCreateManufacturerMessage(final String name, final String country) {
    jmsTemplate.send(MANUFACTURERS, session -> {
      final MapMessage mapMessage = session.createMapMessage();
      mapMessage.setString("name", name);
      mapMessage.setString("country", country);
      return mapMessage;
    });
  }
}
