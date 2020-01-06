package pl.sdacademy.wiosnademo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.sdacademy.wiosnademo.model.ErrorResponse;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GenericExceptionHandler {

    @ExceptionHandler(GenericException.class)
    public ErrorResponse handleGenericException(final GenericException exp) {
        return ErrorResponse.builder()
                .message(exp.getMessage())
                .build();
    }
}
