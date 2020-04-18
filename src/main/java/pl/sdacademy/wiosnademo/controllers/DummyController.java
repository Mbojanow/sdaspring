package pl.sdacademy.wiosnademo.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.sdacademy.wiosnademo.domain.Dummy;

@RestController
@RequestMapping("/api/dummy")
public class DummyController {

  @GetMapping
  public Dummy getDummy() {
    return new Dummy("GET");
  }

  @PostMapping
  public Dummy createDummy() {
    return new Dummy("POST");
  }

  @PutMapping
  public Dummy updateDummy() {
    return new Dummy("PUT");
  }

  @PatchMapping
  public Dummy partialyUpdateDummy() {
    return new Dummy("PATCH");
  }

  @DeleteMapping
  public Dummy deleteDummy() {
    return new Dummy("DELETE");
  }
}
