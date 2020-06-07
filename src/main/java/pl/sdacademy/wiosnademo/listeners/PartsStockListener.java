package pl.sdacademy.wiosnademo.listeners;

import java.util.Optional;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pl.sdacademy.wiosnademo.config.Queues;
import pl.sdacademy.wiosnademo.domain.PartsStock;
import pl.sdacademy.wiosnademo.model.PartsStockDto;
import pl.sdacademy.wiosnademo.repositories.PartsStockRepository;

@Component
public class PartsStockListener {

  private final JmsTemplate jmsTemplate;
  private final PartsStockRepository partsStockRepository;

  public PartsStockListener(final JmsTemplate jmsTemplate, final PartsStockRepository partsStockRepository) {
    this.jmsTemplate = jmsTemplate;
    this.partsStockRepository = partsStockRepository;
  }

  @Transactional
  @JmsListener(destination = Queues.PARTS_STOCK)
  @SendTo("tmpReturnQueue")
  public String handlePartsStockModification(/*final Message<PartsStockDto> partsStockDtoMessage,*/
                                             @Payload final PartsStockDto partsStockDto,
                                             @Header("jms_priority") final String priority) throws JMSException {
    System.out.println("Message send with priority " + priority);
    //final PartsStockDto partsStockDto = partsStockDtoMessage.getPayload();
    final PartsStock updatedPartsStock;

    final Optional<PartsStock> existingPartsStock = partsStockRepository.findByName(partsStockDto.getName());
    if (existingPartsStock.isPresent()) {
      final PartsStock partsStock = existingPartsStock.get();
      partsStock.setStock(partsStock.getStock() + partsStockDto.getStock());
      updatedPartsStock = partsStockRepository.save(partsStock);
    } else {
      updatedPartsStock = partsStockRepository.save(new PartsStock(null, partsStockDto.getName(), partsStockDto.getStock()));
    }
    return "New stock count for " + updatedPartsStock.getName() + " is " + updatedPartsStock.getStock();

    // PO STAREMU
//    jmsTemplate.send(objectMessage.getJMSReplyTo(), session ->
//        session.createTextMessage("New stock count for " + updatedPartsStock.getName()
//            + " is " + updatedPartsStock.getStock()));
  }
}
