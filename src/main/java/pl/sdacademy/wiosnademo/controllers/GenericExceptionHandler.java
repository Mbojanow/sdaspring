package pl.sdacademy.wiosnademo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import pl.sdacademy.wiosnademo.services.ParkingLotException;

@RestControllerAdvice
public class GenericExceptionHandler {

  @ExceptionHandler(ParkingLotException.class)
  public ResponseEntity<String> handleParkingLotException(final ParkingLotException exp) {
    return ResponseEntity
        // wyższy priorytet niż @ResponseStatus
        .status(exp.getStatus() != null ? exp.getStatus() : HttpStatus.BAD_REQUEST.value())
        .body(exp.getMessage());
  }
}
