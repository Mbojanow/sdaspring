package pl.sdacademy.wiosnademo.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import pl.sdacademy.wiosnademo.annotations.HandledByGlobalExceptionHandler;
import pl.sdacademy.wiosnademo.exceptions.SdaException;
import pl.sdacademy.wiosnademo.model.ErrorMessage;

@RestControllerAdvice(annotations = { HandledByGlobalExceptionHandler.class}) // @ControllerAdvice + @ResponseBody -> zawiera @Controller
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(SdaException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorMessage handleSdaException(final SdaException exp,
                                         final HttpServletRequest request) {
    final String requestedFailureUri = request.getRequestURI();
    return new ErrorMessage(exp.getMessage(), List.of(requestedFailureUri));
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorMessage handleException(final Exception exp) {
    log.debug("Unexpected error has occurred");
    return new ErrorMessage("Something went wrong. Please contant support.",
        null);
  }
}
