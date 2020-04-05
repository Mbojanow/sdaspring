package pl.sdacademy.wiosnademo.controllers;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    return new ErrorMessage(exp.getMessage(), List.of());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorMessage handleMethodArgumentNotValidException(final MethodArgumentNotValidException exp) {
    final String object = exp.getBindingResult().getTarget().getClass().getSimpleName();
    final List<String> details = exp.getBindingResult().getFieldErrors().stream()
        .map(fieldError -> fieldError.getDefaultMessage())
        .collect(Collectors.toList());
    return new ErrorMessage("Failed to validate object " + object, details);
  }

  // stworzyli exception handler, ktory wylapuje wyjatki typu NoSuchElementException i zwracali 404
  // i obiekt ErrorMessage z wiadomości "Nie znaleziono poszukiwanego obiektu"
  @ExceptionHandler(NoSuchElementException.class)
  public ResponseEntity<ErrorMessage> handleNoSuchElementException(final NoSuchElementException exp,
                                                                   final HttpServletRequest request) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .header("x-sda", "hi from exception handle")
        .header("x-sda-client-host", request.getRemoteHost())
        .body(new ErrorMessage("Nie znaleziono poszukiwanego obiektu", List.of()));
  }
}
