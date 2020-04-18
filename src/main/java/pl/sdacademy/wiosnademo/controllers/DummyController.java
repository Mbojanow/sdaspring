package pl.sdacademy.wiosnademo.controllers;

import java.lang.annotation.Retention;

import org.springframework.security.access.annotation.Secured;
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

  @Secured("ROLE_READ") // secured oczekuje authority, nie role
  @GetMapping
  public Dummy getDummy() {
    return new Dummy("GET");
  }

  @Secured("ROLE_ADD_OR_MODIFY")
  @PostMapping
  public Dummy createDummy() {
    return new Dummy("POST");
  }

  @Secured("ROLE_ADD_OR_MODIFY")
  @PutMapping
  public Dummy updateDummy() {
    return new Dummy("PUT");
  }

  @Secured("ROLE_ADD_OR_MODIFY")
  @PatchMapping
  public Dummy partialyUpdateDummy() {
    return new Dummy("PATCH");
  }

  @Secured("REMOVE")
  @DeleteMapping
  public Dummy deleteDummy() {
    return new Dummy("DELETE");
  }
}
