package pl.sdacademy.wiosnademo.repository;

import org.springframework.stereotype.Repository;
import pl.sdacademy.wiosnademo.model.Message;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MessageRepository {

    private static long id = 0;

    private List<Message> messages = new ArrayList<>();

    public Message create(final Message message) {
        message.setId(id++);
        messages.add(message);
        return message;
    }

    public List<Message> getAll() {
        return messages;
    }

    public Message getById(final Long id) {
        return messages.stream()
                .filter(message -> message.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }

    public void deleteByIf(final Long id) {
        messages.stream()
                .filter(message -> message.getId().equals(id))
                .findFirst()
                .ifPresent(message -> messages.remove(message));
    }
}
