package pl.sdacademy.wiosnademo.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
@Order(Integer.MIN_VALUE)
public class ExecutionTimeAspect {

  @Value("${log.service.execution.times}")
  private boolean enabled;

  @Pointcut("within(pl.sdacademy.wiosnademo.services..*)")
  private void inServiceLayer() {}

  @Pointcut("execution(public * *(..))")
  private void onPublicMethods() {}

  @Around("inServiceLayer() && onPublicMethods()")
  public Object logExecutionTime(final ProceedingJoinPoint pjp) throws Throwable {
    final long start = System.currentTimeMillis();

    final Object returnedObject = pjp.proceed(pjp.getArgs());

    final long end = System.currentTimeMillis();

    if (enabled) {
      log.info("Execution time: {} ms", (end - start));
    }
    return returnedObject;
  }
}
