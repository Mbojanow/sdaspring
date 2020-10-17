package pl.sdacademy.wiosnademo.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

  private final Map<HttpMethod, Integer> methodToErrorCount = new HashMap<>();

  public GlobalExceptionHandler() {
    Stream.of(HttpMethod.GET, HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE)
        .forEach(method -> methodToErrorCount.put(method, 0));
  }

  @ExceptionHandler(SdaException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorMessage handleSdaException(final SdaException exp,
                                         final HttpServletRequest request) {
    final String method = request.getMethod();
    final HttpMethod httpMethod = HttpMethod.resolve(method);
    methodToErrorCount.computeIfPresent(httpMethod, (m, count) -> ++count);
    final String requestedFailureUri = request.getRequestURI();
    return new ErrorMessage(exp.getMessage(),
        List.of(requestedFailureUri, methodToErrorCount));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorMessage handleMethodArgumentNotValidException(MethodArgumentNotValidException exp) {
    final List<Object> details = exp.getBindingResult().getAllErrors().stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage)
        .collect(Collectors.toUnmodifiableList());
    return new ErrorMessage("Input object is invalid", details);
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorMessage handleException(final Exception exp) {
    log.debug("Unexpected error has occurred");
    return new ErrorMessage("Something went wrong. Please contant support.",
        null);
  }
}
