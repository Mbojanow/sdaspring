package pl.sdacademy.wiosnademo.repositories;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.swing.text.html.Option;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.sdacademy.wiosnademo.domain.ParkingLot;

@Repository
@Transactional
public class ParkingLotRepository {

  private final EntityManager entityManager;

  public ParkingLotRepository(final EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public ParkingLot create(final ParkingLot parkingLot) {
    entityManager.persist(parkingLot);
    return parkingLot;
  }

  public ParkingLot update(final ParkingLot parkingLot) {
    return entityManager.merge(parkingLot);
  }

  public Optional<ParkingLot> findById(final Long id) {
    return Optional.ofNullable(entityManager.find(ParkingLot.class, id));
  }

  @SuppressWarnings("unchecked")
  public List<ParkingLot> getAll() {
    return entityManager.createQuery("SELECT pl FROM parking_lots pl").getResultList();
  }

  public void delete(final Long id) {
    entityManager.createQuery("DELETE FROM parking_lots pl WHERE pl.id = :id")
      .setParameter("id", id)
      .executeUpdate();
  }

  @SuppressWarnings("unchecked")
  public Optional<ParkingLot> findByName(final String name) {
    final List<ParkingLot> parkingLots = entityManager
        .createQuery("SELECT pl FROM parking_lots pl WHERE pl.name = :name")
        .setParameter("name", name).getResultList();
    if (parkingLots.size() == 1) {
      return Optional.of(parkingLots.get(0));
    }
    return Optional.empty();
  }
}
