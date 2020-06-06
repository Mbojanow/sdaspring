package pl.sdacademy.wiosnademo.controllers;

import java.util.List;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import pl.sdacademy.wiosnademo.annotations.SdaSpringTrainingApplication;

@RestControllerAdvice(/*assignableTypes = DummyController.class*/annotations = UseFirstExceptionHandler.class)
//@Order(2)
public class FirstControllerAdvice {

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public List<String> handleException(final Exception exp) {
    return List.of(exp.getMessage(), "I was caught in FIRST error handler");
  }
}
