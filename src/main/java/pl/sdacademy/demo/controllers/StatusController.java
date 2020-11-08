package pl.sdacademy.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

  @GetMapping("/status")
  public Status getStatus() {
    return new Status("UP");
  }
}
