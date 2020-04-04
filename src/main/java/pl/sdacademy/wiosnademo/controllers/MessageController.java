package pl.sdacademy.wiosnademo.controllers;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pl.sdacademy.wiosnademo.domain.ErrorMessage;
import pl.sdacademy.wiosnademo.domain.Message;
import pl.sdacademy.wiosnademo.domain.Messages;
import pl.sdacademy.wiosnademo.repostories.MessageRepository;

//@Controller
//@ResponseBody
@RestController // = @Controller + @ResponseBody
@RequestMapping(value = "/api/messages",
    consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, // Content-Type
    produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }) // Accept
public class MessageController {

  private final MessageRepository messageRepository;

  public MessageController(final MessageRepository messageRepository) {
    this.messageRepository = messageRepository;
  }

  //@RequestMapping(method = RequestMethod.GET) // /api/messages
  @GetMapping
  public Messages getAllMessages() {
    return new Messages(messageRepository.getAll());
  }

  //@RequestMapping(path = "/{id}", method = RequestMethod.GET)
  @GetMapping(path = "/{id}")
  public Message getMessageById(@PathVariable(name = "id") final Long id) {
    return messageRepository.findById(id)
        .orElseThrow();
  }

  //@RequestMapping(method = RequestMethod.POST)
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Message createMessage(@RequestBody final Message message) {
    return messageRepository.createMessage(message);
  }

  //api/messages/14
  //@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
  @DeleteMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteById(@PathVariable(name = "id") final Long id) {
    messageRepository.deleteById(id);
  }
}
