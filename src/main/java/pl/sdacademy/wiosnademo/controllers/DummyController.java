package pl.sdacademy.wiosnademo.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pl.sdacademy.wiosnademo.model.Dummy;

@RestController
@RequestMapping("/api/dummy")
public class DummyController {

  @GetMapping
  public ResponseEntity<Dummy> getDummy() {
    //return new Dummy("asd");
    //ResponseEntity.badRequest().body(...)
    return ResponseEntity.ok(new Dummy("asd"));
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
  public void delete(final HttpServletResponse response) {
    response.setStatus(200);
  }
}
