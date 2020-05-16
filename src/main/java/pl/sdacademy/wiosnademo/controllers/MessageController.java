package pl.sdacademy.wiosnademo.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.sdacademy.wiosnademo.model.Message;
import pl.sdacademy.wiosnademo.repositories.MessageRepository;

@Controller
@RequestMapping("/api/messages")
public class MessageController {

  private final MessageRepository messageRepository;

  public MessageController(final MessageRepository messageRepository) {
    this.messageRepository = messageRepository;
  }

  @ResponseBody
  @RequestMapping(method = RequestMethod.GET)
  public List<Message> findAllMessage() {
    return messageRepository.findAll();
  }

}
