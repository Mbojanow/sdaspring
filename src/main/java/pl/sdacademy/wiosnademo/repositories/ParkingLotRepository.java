package pl.sdacademy.wiosnademo.repositories;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

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

  // pobrania wszystkich, pobrania po id, stworzenia, usuniecia po 1, aktualizacji

  public ParkingLot create(final ParkingLot parkingLot) {
    entityManager.persist(parkingLot);
    return parkingLot;
  }

  public List<ParkingLot> getAll() {
    return entityManager.createQuery("SELECT pl FROM parking_lots pl", ParkingLot.class)
        .getResultList();
  }

  public Optional<ParkingLot> findById(final Long id) {
    return Optional.ofNullable(entityManager.find(ParkingLot.class, id));
  }

  public ParkingLot update(final ParkingLot parkingLot) {
    return entityManager.merge(parkingLot);
  }

  public void delete(final Long id) {
    entityManager.createQuery("DELETE FROM parking_lots pl WHERE pl.id = :id")
        .setParameter("id", id)
        .executeUpdate();
    //entityManager.remove(ParkingLot);
  }

  public Optional<ParkingLot> findByName(final String name) {
    final List<ParkingLot> results = entityManager.createQuery("SELECT pl FROM parking_lots pl WHERE pl.name = :name", ParkingLot.class)
        .setParameter("name", name)
        .getResultList(); // działamy na liście a nie na getFirstResult -> bo pobranie listy NIE wyrzuca wyjątki, Wyajtki chcemy na warstwie serwisowej
    if (results.size() == 1) {
      return Optional.of(results.get(0));
    }
    return Optional.empty();
  }
}
