package pl.sdacademy.wiosnademo.listeners;

import static pl.sdacademy.wiosnademo.config.Queues.MANUFACTURERS;

import java.util.List;

import javax.jms.JMSException;
import javax.jms.MapMessage;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pl.sdacademy.wiosnademo.domain.Manufacturer;
import pl.sdacademy.wiosnademo.repositories.ManufacturerRepository;

@Component
@Transactional
public class ManufacturerListener {

  private final ManufacturerRepository manufacturerRepository;

  public ManufacturerListener(final ManufacturerRepository manufacturerRepository) {
    this.manufacturerRepository = manufacturerRepository;
  }

  @JmsListener(destination = MANUFACTURERS)
  public void handleCreateManufacturerMessage(final MapMessage mapMessage) throws JMSException {
    final String name = mapMessage.getString("name");
    final String country = mapMessage.getString("country");
    final Manufacturer manufacturer = manufacturerRepository.save(new Manufacturer(null, name, country, List.of()));
    System.out.println("Manufacturer with id " + manufacturer.getId() + " saved.");
  }
}
