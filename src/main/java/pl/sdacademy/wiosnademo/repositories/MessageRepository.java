package pl.sdacademy.wiosnademo.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import pl.sdacademy.wiosnademo.model.Message;

@Repository
public class MessageRepository {
  //dodaj metodę do stworzenia, pobrania po id, pobrania wszystkich, usunięcia pod id obiektu typu message.

  private static long index = 0;

  private List<Message> messages = new ArrayList<>();

  public Message create(final Message message) {
    message.setId(++index);
    messages.add(message);
    return message;
  }

  public Optional<Message> findById(final Long id) {
    return messages.stream()
        .filter(message -> message.getId().equals(id))
        .findFirst();
  }

  public List<Message> findAll() {
    return messages;
  }

  public void deleteById(final Long id) {
    findById(id)
        .ifPresent(message -> messages.remove(message));
  }

}
