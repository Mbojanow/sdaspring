package pl.sdacademy.wiosnademo.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class EntryExitLoggerAspect {

  @Pointcut("@annotation(pl.sdacademy.wiosnademo.aspects.EntryExitLogger)")
  private void aroundEntryAndExitAnnotation() {}

  @Around("aroundEntryAndExitAnnotation()")
  public Object logEntryAndExit(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    log.info("Entering method {}", proceedingJoinPoint.getSignature().getName());
    final Object returnedObject = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
    log.info("Method executed {}", proceedingJoinPoint.getSignature().getName());
    return returnedObject;
  }
}
