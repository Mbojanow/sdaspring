package pl.sdacademy.wiosnademo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.wiosnademo.model.Message;
import pl.sdacademy.wiosnademo.repository.MessageRepository;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping(path = "/api/v1/messages")
public class MessageController {

    private final MessageRepository messageRepository;

    //@Autowired
    public MessageController(final MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Message> getAll() {
        return messageRepository.getAll();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Message getById(@PathVariable("id") final Long id) {
        return messageRepository.getById(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Message create(@RequestBody final Message message) {
        return messageRepository.create(message);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") final Long id) {
        messageRepository.deleteByIf(id);
    }

}
