package pl.sdacademy.wiosnademo.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import pl.sdacademy.wiosnademo.model.ErrorMessage;

@RestControllerAdvice // @Component w srodku
public class GlobalExceptionHandler {

  @ExceptionHandler(Exception.class)
  //@ResponseStatus(HttpStatus.BAD_REQUEST) // status w ResponseEntity ma priorytet
  public ResponseEntity<ErrorMessage> handlException(final Exception exp, final HttpServletRequest request) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .header("x-sda-javagda32", "no-siema")
        .body(ErrorMessage.builder()
            .message(exp.getMessage())
            .details(List.of(request.getRemoteHost()))
            .build());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorMessage handleMethodArgumentNotValidException(final MethodArgumentNotValidException exp) {
    final String validatedObject = exp.getBindingResult().getTarget().getClass().getSimpleName();
    final List<String> details = exp.getBindingResult().getFieldErrors().stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage)
        .collect(Collectors.toList());
    return ErrorMessage.builder()
        .message("Failed to validate object: " + validatedObject)
        .details(details)
        .build();
  }

//  @ExceptionHandler(SdaException.class)
//  ...

}
