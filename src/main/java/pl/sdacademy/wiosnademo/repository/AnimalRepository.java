package pl.sdacademy.wiosnademo.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.sdacademy.wiosnademo.domain.Animal;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class AnimalRepository {

  private final EntityManager entityManager;

  public AnimalRepository(final EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public Animal getById(final Long id) {
    return entityManager.find(Animal.class, id);
  }

  @SuppressWarnings("unchecked")
  public List<Animal> getAll() {
    return entityManager.createQuery("SELECT a FROM animals a").getResultList();
  }

  public Animal create(final Animal animal) {
    entityManager.persist(animal);
    return animal;
  }

  public Animal update(final Animal animal) {
    return entityManager.merge(animal);
  }

  public void delete(final Long id) {
    Optional.ofNullable(getById(id)).ifPresent(entityManager::remove);
  }

}
