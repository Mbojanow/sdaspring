package pl.sdacademy.wiosnademo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.sdacademy.wiosnademo.model.ErrorMessage;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleGenericException(final Exception exception, final HttpServletRequest request) {
        return ErrorMessage.builder()
                .message(exception.getMessage())
                .details(List.of(request.getHeader("host")))
                .build();
    }

}
