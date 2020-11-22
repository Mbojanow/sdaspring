package pl.sdacademy.wiosnademo.aspects;

import java.util.stream.Stream;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.SecurityContextProvider;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import pl.sdacademy.wiosnademo.model.AnimalDto;

@Component
@Aspect
@Slf4j
public class RequestBodyLoggerAspect {

  private final boolean shouldLogBody;
  private final ObjectMapper objectMapper;

  public RequestBodyLoggerAspect(@Value("${log.animal.request.body:false}") final boolean shouldLogBody,
                                 final ObjectMapper objectMapper) {
    this.shouldLogBody = shouldLogBody;
    this.objectMapper = objectMapper;
  }

  @Pointcut("within(pl.sdacademy.wiosnademo.controllers.*)")
  private void onWebLayer() {}

  @Pointcut("args(pl.sdacademy.wiosnademo.model.AnimalDto,..)")
  private void onMethodWithAnimalDtoAsFirstArgument() {}

  @Pointcut("args(..,pl.sdacademy.wiosnademo.model.AnimalDto)")
  private void onMethodWithAnimalDtoAsLastArgument() {}

  @Around("onWebLayer() && (onMethodWithAnimalDtoAsFirstArgument() || onMethodWithAnimalDtoAsLastArgument())")
  public Object logAnimalRequestBody(final ProceedingJoinPoint joinPoint) throws Throwable {
    if (shouldLogBody) {
      Stream.of(joinPoint.getArgs())
          .filter(arg -> arg instanceof AnimalDto)
          .map(x -> (AnimalDto)x)
          .findFirst()
          .ifPresent(this::logAnimalDto);
    }
    return joinPoint.proceed(joinPoint.getArgs());
  }

  private void logAnimalDto(final AnimalDto animalDto) {
    try {
      log.info("Body in request: {}", objectMapper.writeValueAsString(animalDto));
    } catch (JsonProcessingException e) {
      log.warn("Could not log request body");
    }
  }
}
