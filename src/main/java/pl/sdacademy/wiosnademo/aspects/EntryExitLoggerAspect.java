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
  private void onEntryExitLoggerAnnotation() {}

  @Around("onEntryExitLoggerAnnotation()")
  public Object logEntryAndExit(final ProceedingJoinPoint joinPoint) throws Throwable {
    // ProceedingJoinPoint - metoda, która spełniła warunek pointcutów

    // wywołać funkcjonalność Before
    log.info("Entering method: {}", joinPoint.getSignature().getName());

    // proceed - wywołanie metody
    final Object returnedObject = joinPoint.proceed(joinPoint.getArgs());

    // wywołać funkcjonalnośći "after"
    log.info("Exiting method: {}", joinPoint.getSignature().getName());
    return returnedObject;
  }
}
