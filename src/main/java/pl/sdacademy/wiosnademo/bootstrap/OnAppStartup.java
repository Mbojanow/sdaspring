package pl.sdacademy.wiosnademo.bootstrap;

import javax.jms.JMSException;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import pl.sdacademy.wiosnademo.producers.ManufacturerMessageProducer;
import pl.sdacademy.wiosnademo.producers.PartsStockProducer;

@Component
public class OnAppStartup implements ApplicationListener<ContextRefreshedEvent> {

  private final ManufacturerMessageProducer manufacturerMessageProducer;
  private final PartsStockProducer partsStockProducer;

  public OnAppStartup(final ManufacturerMessageProducer manufacturerMessageProducer,
                      final PartsStockProducer partsStockProducer) {
    this.manufacturerMessageProducer = manufacturerMessageProducer;
    this.partsStockProducer = partsStockProducer;
  }

  @Override
  public void onApplicationEvent(final ContextRefreshedEvent contextRefreshedEvent) {
    manufacturerMessageProducer.sentCreateManufacturerMessage("Toyota", "Japan");

    try {
      partsStockProducer.addToStock("screwwhatever", 3L);
      partsStockProducer.addToStock("screwwhatever", 5L);
      partsStockProducer.addToStock("wheels", 4L);
    } catch (JMSException e) {
      e.printStackTrace();
    }
  }
}
