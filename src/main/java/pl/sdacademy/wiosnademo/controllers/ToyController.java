package pl.sdacademy.wiosnademo.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pl.sdacademy.wiosnademo.model.ToyDto;
import pl.sdacademy.wiosnademo.services.ToyService;

@RestController
//@RequestMapping("/api/toys")
public class ToyController {

  private final ToyService toyService;

  public ToyController(final ToyService toyService) {
    this.toyService = toyService;
  }

  @PostMapping("/api/toys")
  @ResponseStatus(HttpStatus.CREATED)
  public ToyDto createToy(@Valid @RequestBody ToyDto toyDto) {
    return toyService.create(toyDto);
  }
}
