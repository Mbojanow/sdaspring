package pl.sdacademy.wiosnademo.listeners;

import java.util.Optional;

import javax.jms.JMSException;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pl.sdacademy.wiosnademo.config.Queues;
import pl.sdacademy.wiosnademo.domain.MessagesCounter;
import pl.sdacademy.wiosnademo.domain.PartsStock;
import pl.sdacademy.wiosnademo.model.PartsStockDto;
import pl.sdacademy.wiosnademo.repositories.MessagesCounterRepository;
import pl.sdacademy.wiosnademo.repositories.PartsStockRepository;

@Component
public class PartsStockListener {

  private final JmsTemplate jmsTemplate;
  private final PartsStockRepository partsStockRepository;
  private final MessagesCounterRepository messagesCounterRepository;

  public PartsStockListener(final JmsTemplate jmsTemplate, final PartsStockRepository partsStockRepository,
                            final MessagesCounterRepository messagesCounterRepository) {
    this.jmsTemplate = jmsTemplate;
    this.partsStockRepository = partsStockRepository;
    this.messagesCounterRepository = messagesCounterRepository;
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


  @Transactional
  @JmsListener(destination = Queues.PARTS_STOCK)
  public void handleMessageCountIncrement() {
    final MessagesCounter messagesCounter = messagesCounterRepository.getOne(1L);
    messagesCounter.setCounter(messagesCounter.getCounter() + 1);
    messagesCounterRepository.save(messagesCounter);
    System.out.println("New count is " + messagesCounter.getCounter());
  }
}
