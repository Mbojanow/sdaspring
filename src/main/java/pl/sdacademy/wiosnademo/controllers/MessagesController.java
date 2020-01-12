package pl.sdacademy.wiosnademo.controllers;

import java.util.List;

import javax.validation.Valid;

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

import pl.sdacademy.wiosnademo.domain.Message;
import pl.sdacademy.wiosnademo.domain.Messages;
import pl.sdacademy.wiosnademo.domain.SimpleError;
import pl.sdacademy.wiosnademo.repositories.MessageRepository;

// albo path albo value - domyslne
@RequestMapping(value = "/api/v1/messages",
    consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, // Content-Type
  produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }) // Accept
//@Controller // rejestracja w IoC i rejestracja w DispatcherServlet
//@ResponseBody
@RestController // == @Controller + @ResponseBody
public class MessagesController {

  private final MessageRepository messageRepository;

  public MessagesController(final MessageRepository messageRepository) {
    this.messageRepository = messageRepository;
  }

  @GetMapping
  //@RequestMapping(method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.OK)
  public Messages getAllMessages() {
    return new Messages(messageRepository.getAll());
  }

  @GetMapping(path = "/{id}")
  //@RequestMapping(path = "/{id}", method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.OK)
  public Message getMessage(@PathVariable("id") final Long id) {
    return messageRepository.getById(id)
        .orElseThrow();
  }

  @PostMapping
  //@RequestMapping(method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  public Message create(@Valid @RequestBody final Message message) {
    return messageRepository.createMessage(message);
  }

  @DeleteMapping(path = "/{id}")
  //@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("id") final Long id) {
    messageRepository.delete(id);
  }

//  @ExceptionHandler(Exception.class)
////  public ResponseEntity<SimpleError> handleException(final Exception exp) {
////    return ResponseEntity.badRequest().body(new SimpleError(exp.getMessage()));
////  }

//  @ExceptionHandler(Exception.class)
//  @ResponseStatus(HttpStatus.BAD_REQUEST)
//  public SimpleError handleException2(final Exception exp) {
//    return new SimpleError(exp.getMessage());
//  }


}
