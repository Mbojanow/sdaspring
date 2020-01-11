package pl.sdacademy.wiosnademo.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import pl.sdacademy.wiosnademo.domain.Message;
import pl.sdacademy.wiosnademo.repositories.MessageRepository;

// albo path albo value - domyslne
@RequestMapping("/api/v1/messages")
@Controller // rejestracja w IoC i rejestracja w DispatcherServlet
@ResponseBody
public class MessagesController {

  private final MessageRepository messageRepository;

  public MessagesController(final MessageRepository messageRepository) {
    this.messageRepository = messageRepository;
  }

  @RequestMapping(method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.OK)
  public List<Message> getAllMessages() {
    return messageRepository.getAll();
  }
}
