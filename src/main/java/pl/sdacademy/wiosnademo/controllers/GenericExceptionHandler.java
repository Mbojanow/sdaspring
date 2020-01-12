package pl.sdacademy.wiosnademo.controllers;

import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import pl.sdacademy.wiosnademo.domain.ErrorResponse;

@RestControllerAdvice // @Controller + @ResponseBody
public class GenericExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleMethodArgumentNotValidException(final MethodArgumentNotValidException exp) {
    //exp.getBindingResult().getFieldErrors().get(0).getDefaultMessage()
    final List<String> details = exp.getBindingResult().getFieldErrors().stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage)
        .collect(Collectors.toList());
    return new ErrorResponse("Failed to validate object", details);
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleException(final Exception exp, final HttpServletRequest request) {
    final Enumeration<String> host = request.getHeaders("Host");
    return new ErrorResponse(exp.getMessage(), List.of(host.nextElement()));
  }
}
