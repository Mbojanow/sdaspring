package pl.sdacademy.wiosnademo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.sdacademy.wiosnademo.producers.HelloWorldMessageProducer;

@RestController
@RequestMapping("/api/messages")
public class HelloWorldController {

  private final HelloWorldMessageProducer helloWorldMessageProducer;

  public HelloWorldController(final HelloWorldMessageProducer helloWorldMessageProducer) {
    this.helloWorldMessageProducer = helloWorldMessageProducer;
  }

  @GetMapping
  public void sendFirstJmsMessage() {
    helloWorldMessageProducer.sendHelloWorld();
  }
}
