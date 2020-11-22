package pl.sdacademy.wiosnademo.aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class EntityManagerAspect {

  private static int entityManagerInteraction = 0;

  @Pointcut("target(javax.persistence.EntityManager)")
  private void onEntityManager() {}

  @After("onEntityManager()")
  public void logEntityManagerInteractionInCurrentApplicationSession() {
    log.debug("Number of database interaction with EntityManager is {}", ++entityManagerInteraction);
  }
}
