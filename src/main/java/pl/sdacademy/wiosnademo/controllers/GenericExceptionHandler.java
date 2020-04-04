package pl.sdacademy.wiosnademo.controllers;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import pl.sdacademy.wiosnademo.domain.ErrorMessage;

//@ControllerAdvice
//@ResponseBody
@RestControllerAdvice // @ControllerAdvice + @ResponseBody
// RestControllerAdvice -> ControllerAdvice -> Component
public class GenericExceptionHandler {

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorMessage handleGenericException(final Exception exp) {
    return new ErrorMessage(exp.getMessage());
  }

  // stworzyli exception handler, ktory wylapuje wyjatki typu NoSuchElementException i zwracali 404
  // i obiekt ErrorMessage z wiadomości "Nie znaleziono poszukiwanego obiektu"
  @ExceptionHandler(NoSuchElementException.class)
  public ResponseEntity<ErrorMessage> handleNoSuchElementException(final NoSuchElementException exp) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .header("x-sda", "hi from exception handle")
        .body(new ErrorMessage("Nie znaleziono poszukiwanego obiektu"));
  }
}
