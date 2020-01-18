package pl.sdacademy.wiosnademo.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.sdacademy.wiosnademo.model.Dummy;

@RestController
@RequestMapping("/api/dummy")
public class DummyController {

  @GetMapping
  public Dummy getDummy() {
    return new Dummy("asd");
  }

  @PostMapping
  public Dummy createDummy() {
    return new Dummy("qwe");
  }

  @PutMapping
  public Dummy updateDummy() {
    return new Dummy("update");
  }

  @DeleteMapping
  public void delete() {
  }
}
