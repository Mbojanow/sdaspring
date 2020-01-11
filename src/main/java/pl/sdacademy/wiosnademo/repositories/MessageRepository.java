package pl.sdacademy.wiosnademo.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import pl.sdacademy.wiosnademo.domain.Message;

@Repository
public class MessageRepository {

  //Dodaj komponent typu repository, który przechowuje obiekty typu Message w liście (
  // do baz danych wrócimy!),
  // dodaj metodę do stworzenia, pobrania po id,
  // pobrania wszystkich, usunięcia pod id obiektu typu message.

  private static long id = 0;

  private List<Message> messages = new ArrayList<>();
  // ponizsze wersje sa IMMUTABLE
  // Collections.emptyList();
  // List.of();

  public Message createMessage(final Message message) {
    message.setId(id++);
    messages.add(message);
    return message;
  }

  public Optional<Message> getById(final Long id) {
    return messages.stream()
        .filter(message -> message.getId().equals(id))
        .findFirst();
  }

  public void delete(final Long id) {
    messages.stream()
        .filter(message -> message.getId().equals(id))
        .findFirst()
        .ifPresent(message -> messages.remove(message));
  }

  public List<Message> getAll() {
    return messages;
  }
}
