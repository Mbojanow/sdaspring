package pl.sdacademy.wiosnademo.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import pl.sdacademy.wiosnademo.model.ErrorMessage;
import pl.sdacademy.wiosnademo.model.Message;
import pl.sdacademy.wiosnademo.model.Messages;
import pl.sdacademy.wiosnademo.repositories.MessageRepository;

@Controller
@RequestMapping(value = "/api/messages",
    consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
    produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@ResponseBody // automatycznie kleci na wszystkie metody publiczne w kontrolerze
public class MessageController {

  private final MessageRepository messageRepository;

  public MessageController(final MessageRepository messageRepository) {
    this.messageRepository = messageRepository;
  }

  //@ResponseBody
  //@RequestMapping(method = RequestMethod.GET)
  @GetMapping
  public Messages findAllMessage() {
    return new Messages(messageRepository.findAll());
  }

  //@RequestMapping(path = "/{id}", method = RequestMethod.GET)
  //@ResponseBody
  @GetMapping(path = "/{id}")
  public Message findById(@PathVariable("id") final Long id) {
    return messageRepository.findById(id)
        .orElseThrow(() -> new SdaException("Message with id " + id + " not found"));
  }

  @ResponseStatus(HttpStatus.CREATED)
  //@ResponseBody
  //@RequestMapping(method = RequestMethod.POST)
  @PostMapping
  public Message createMessage(@RequestBody final Message message) {
    return messageRepository.create(message);
  }

//  @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
  @DeleteMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteById(@PathVariable final Long id) {
    messageRepository.deleteById(id);
  }
}
