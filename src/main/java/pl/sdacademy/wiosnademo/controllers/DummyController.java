package pl.sdacademy.wiosnademo.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pl.sdacademy.wiosnademo.domain.Dummy;

@RestController
@RequestMapping("/api/dummy")
public class DummyController {

  @Secured("ROLE_READ")
  @GetMapping
  public List<Dummy> getDummies() {
    return List.of(new Dummy("Andrzej"));
  }

  @Secured("ROLE_READ")
  @GetMapping("/{name}")
  public Dummy getDummy(@PathVariable final String name) {
    return new Dummy(name);
  }

  @Secured("ROLE_ADD_OR_MODIFY")
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Dummy createDummy() {
    return new Dummy("Created dummy");
  }

  @Secured("ROLE_ADD_OR_MODIFY")
  @PutMapping("/{name}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void updateDummy() {
  }

  @Secured("ROLE_REMOVE")
  @DeleteMapping
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteDummy() {

  }
}
