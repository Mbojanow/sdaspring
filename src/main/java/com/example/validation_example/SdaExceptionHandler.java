package com.example.validation_example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // @ControllerAdvice + @ResponseBody
public class SdaExceptionHandler {

    @ExceptionHandler(SdaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handle(SdaException exp) {
        return new Error(exp.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handle(MethodArgumentNotValidException exp) {
        return new Error("Boom");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Error> handle(Exception exp) {
        return ResponseEntity.internalServerError().body(new Error(exp.getMessage()));
    }


}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Error {
    private String message;
}