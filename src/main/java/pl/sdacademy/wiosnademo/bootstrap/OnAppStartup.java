package pl.sdacademy.wiosnademo.bootstrap;

import javax.jms.JMSException;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import lombok.SneakyThrows;
import pl.sdacademy.wiosnademo.domain.MessagesCounter;
import pl.sdacademy.wiosnademo.producers.ManufacturerMessageProducer;
import pl.sdacademy.wiosnademo.producers.PartsStockProducer;
import pl.sdacademy.wiosnademo.repositories.MessagesCounterRepository;

@Component
public class OnAppStartup implements ApplicationListener<ContextRefreshedEvent> {

  private final ManufacturerMessageProducer manufacturerMessageProducer;
  private final PartsStockProducer partsStockProducer;
  private final MessagesCounterRepository messagesCounterRepository;

  public OnAppStartup(final ManufacturerMessageProducer manufacturerMessageProducer,
                      final PartsStockProducer partsStockProducer,
                      final MessagesCounterRepository messagesCounterRepository) {
    this.manufacturerMessageProducer = manufacturerMessageProducer;
    this.partsStockProducer = partsStockProducer;
    this.messagesCounterRepository = messagesCounterRepository;
  }

  @SneakyThrows
  @Override
  public void onApplicationEvent(final ContextRefreshedEvent contextRefreshedEvent) {
    manufacturerMessageProducer.sentCreateManufacturerMessage("Toyota", "Japan");
    messagesCounterRepository.save(new MessagesCounter(null, 0L));
    Thread.sleep(1000L);

    try {
      partsStockProducer.addToStock("screwwhatever", 3L);
      partsStockProducer.addToStock("screwwhatever", 5L);
      partsStockProducer.addToStock("wheels", 4L);
    } catch (JMSException e) {
      e.printStackTrace();
    }
  }
}
