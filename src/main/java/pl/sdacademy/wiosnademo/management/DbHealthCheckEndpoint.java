package pl.sdacademy.wiosnademo.management;

import javax.persistence.EntityManager;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import lombok.RequiredArgsConstructor;

@Component
@Endpoint(id = "db-health", enableByDefault = false)
@RequiredArgsConstructor
public class DbHealthCheckEndpoint {

  private final EntityManager entityManager;
  private final TransactionTemplate transactionTemplate;

  @ReadOperation // @GetMapping
  // zwraca nazwę aktualnej bazy danych lub "DOWN" jeżeli baza nie działa
  public String getDbStatus() {
    try {
      return (String) transactionTemplate.execute(transactionStatus -> entityManager
          .createNativeQuery("SELECT DATABASE() as DB").getSingleResult());
    } catch (Exception exp) {
      return "DOWN";
    }
  }
}
