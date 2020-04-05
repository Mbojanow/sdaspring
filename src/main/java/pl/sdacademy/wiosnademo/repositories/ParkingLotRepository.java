package pl.sdacademy.wiosnademo.repositories;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import pl.sdacademy.wiosnademo.domain.ParkingLot;

@Repository
public class ParkingLotRepository {

  private final EntityManager entityManager;

  public ParkingLotRepository(final EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public Optional<ParkingLot> findById(final Long id) {
    return Optional.ofNullable(entityManager.find(ParkingLot.class, id));
  }

  public List<ParkingLot> findAll() {
    return entityManager
        .createQuery("SELECT pl FROM parking_lots pl", ParkingLot.class)
        .getResultList();
  }

  public ParkingLot create(final ParkingLot parkingLot) {
    entityManager.persist(parkingLot);
    return parkingLot;
  }

  public ParkingLot update(final ParkingLot parkingLot) {
    return entityManager.merge(parkingLot);
  }

  public void delete(final Long id) {
    entityManager.createQuery("DELETE FROM parking_lots WHERE id = :id")
        .setParameter("id", id)
        .executeUpdate();
  }

  public ParkingLot findByName(final String name) {
    return entityManager.createQuery("SELECT pl FROM parking_lots pl WHERE pl.name = :name", ParkingLot.class)
        .setParameter("name", name)
        .getSingleResult();
  }
}
