package pl.sdacademy.wiosnademo.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class ExecutionTimeLoggingAspect implements Ordered {

  private final boolean shouldLogExecutionTime;

  public ExecutionTimeLoggingAspect(@Value("${log.service.execution.times:false}") final boolean shouldLogExecutionTime) {
    this.shouldLogExecutionTime = shouldLogExecutionTime;
  }

  @Pointcut("within(pl.sdacademy.wiosnademo.services.*)")
  private void onServiceLayer() {}

  @Pointcut("execution(public * *(..))")
  private void onAnyPublicMethod() {}

  @Around("onServiceLayer() && onAnyPublicMethod()")
  public Object logExecutionTime(final ProceedingJoinPoint joinPoint) throws Throwable {
    final long startingTimestamp = System.currentTimeMillis();
    final Object returnedObject = joinPoint.proceed(joinPoint.getArgs());
    final long endTimestamp = System.currentTimeMillis();
    if (shouldLogExecutionTime) {
      log.info("Method {} took {} millis to execute", joinPoint.getSignature().getName(), (endTimestamp - startingTimestamp));
    }
    return returnedObject;
  }


  @Override
  public int getOrder() {
    return Ordered.HIGHEST_PRECEDENCE;
  }
}
