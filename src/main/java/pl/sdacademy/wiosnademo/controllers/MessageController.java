package pl.sdacademy.wiosnademo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.wiosnademo.model.Message;
import pl.sdacademy.wiosnademo.model.Messages;
import pl.sdacademy.wiosnademo.repository.MessageRepository;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/messages", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class MessageController {

    private final MessageRepository messageRepository;

    public MessageController(final MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping
    public Messages getAll() {
        return new Messages(messageRepository.getAll());
    }

    @GetMapping(path = "/{id}")
    public Message getById(@PathVariable("id") final Long id) {
        return messageRepository.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Message create(@Valid @RequestBody final Message message) {
        return messageRepository.create(message);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") final Long id) {
        messageRepository.deleteById(id);
    }


//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<SimpleError> handle(final Exception exp, final HttpServletRequest request) {
//        return ResponseEntity.badRequest().body(new SimpleError(exp.getMessage()));
//    }
}
