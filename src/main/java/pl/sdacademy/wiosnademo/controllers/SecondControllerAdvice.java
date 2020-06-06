package pl.sdacademy.wiosnademo.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "pl.sdacademy.wiosnademo")
//@Order(1)
public class SecondControllerAdvice {

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public List<String> handleException(final Exception exp) {
    return List.of(exp.getMessage(), "I was caught in SECOND error handler");
  }
}
