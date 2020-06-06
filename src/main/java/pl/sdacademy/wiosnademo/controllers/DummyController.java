package pl.sdacademy.wiosnademo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@UseFirstExceptionHandler
@RestController
@RequestMapping("/api/v1/dummy")
public class DummyController {

  @GetMapping
  public void dummy() {
    throw new UnsupportedOperationException("you dummy, you cannot do this...");
  }
}
