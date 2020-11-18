package pl.sdacademy.wiosnademo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.sdacademy.wiosnademo.exception.SdaException;

@RestControllerAdvice
public class SimpleExceptionHandler {

  @ExceptionHandler(SdaException.class)
  public ResponseEntity<Object> handleSdaException(final SdaException exp) {
    return ResponseEntity.status(exp.getStatus()).body("Something went wrong");
  }
}
