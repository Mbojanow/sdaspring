package pl.sdacademy.wiosnademo.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
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

  @GetMapping
  public List<Dummy> getDummies() {
    return List.of(new Dummy("Andrzej"));
  }

  @GetMapping("/{name}")
  public Dummy getDummy(@PathVariable final String name) {
    return new Dummy(name);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Dummy createDummy() {
    return new Dummy("Created dummy");
  }

  @PutMapping("/{name}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void updateDummy() {
  }

  @DeleteMapping
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteDummy() {

  }
}
