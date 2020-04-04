package pl.sdacademy.wiosnademo.repostories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import pl.sdacademy.wiosnademo.domain.Message;

@Repository
public class MessageRepository {

  private static long currentIndex = 0;

  private List<Message> messages = new ArrayList<>();

  /* dodaj metodę do stworzenia, pobrania po id, pobrania wszystkich, usunięcia pod id obiektu typu message.*/

  public Message createMessage(final Message message) {
    message.setId(++currentIndex);
    messages.add(message);
    return message;
  }

  public Optional<Message> findById(final Long id) {
    return messages.stream()
        .filter(message -> message.getId().equals(id))
        .findFirst();
  }

  public List<Message> getAll() {
    return messages;
  }

  public void deleteById(final Long id) {
    //messages.removeIf(message -> message.getId().equals(id)); //nieoptymalne
    findById(id).ifPresent(message -> messages.remove(message));
  }
}
