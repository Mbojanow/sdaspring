package pl.sdacademy.wiosnademo.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import pl.sdacademy.wiosnademo.domain.Message;
import pl.sdacademy.wiosnademo.repostories.MessageRepository;

@Controller
@ResponseBody
@RequestMapping("/api/messages")
public class MessageController {

  private final MessageRepository messageRepository;

  public MessageController(final MessageRepository messageRepository) {
    this.messageRepository = messageRepository;
  }

  @RequestMapping(method = RequestMethod.GET) // /api/messages
  public List<Message> getAllMessages() {
    return messageRepository.getAll();
  }

  @RequestMapping(path = "/{id}", method = RequestMethod.GET)
  public Message getMessageById(@PathVariable(name = "id") final Long id) {
    return messageRepository.findById(id).orElse(null);
  }

  @RequestMapping(method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  public Message createMessage(@RequestBody final Message message) {
    return messageRepository.createMessage(message);
  }

  //api/messages/14
  @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteById(@PathVariable(name = "id") final Long id) {
    messageRepository.deleteById(id);
  }
}
