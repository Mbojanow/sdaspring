package pl.sdacademy.wiosnademo.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import pl.sdacademy.wiosnademo.producers.ManufacturerMessageProducer;

@Component
public class OnAppStartup implements ApplicationListener<ContextRefreshedEvent> {

  private final ManufacturerMessageProducer manufacturerMessageProducer;

  public OnAppStartup(final ManufacturerMessageProducer manufacturerMessageProducer) {
    this.manufacturerMessageProducer = manufacturerMessageProducer;
  }

  @Override
  public void onApplicationEvent(final ContextRefreshedEvent contextRefreshedEvent) {
    manufacturerMessageProducer.sentCreateManufacturerMessage("Toyota", "Japan");
  }
}
