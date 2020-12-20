package pl.sdacademy.wiosnademo.aspects;

import java.util.concurrent.atomic.AtomicInteger;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class EntityManagerAspect {

  private final AtomicInteger value = new AtomicInteger();

  @Pointcut("target(javax.persistence.EntityManager)")
  private void afterEntityManagerAction() {}

  @After("afterEntityManagerAction()") // advice
  public void logInteractionsNum() {
    log.info("Current interactions with entity manager: " + value.incrementAndGet());
  }
}
