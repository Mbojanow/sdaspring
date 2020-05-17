package pl.sdacademy.wiosnademo.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

//  @ExceptionHandler(SdaException.class)
//  ...

}
