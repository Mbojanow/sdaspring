package pl.sdacademy.wiosnademo.repositories;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.swing.text.html.Option;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import pl.sdacademy.wiosnademo.domain.Shop;

@Transactional
@Repository
@RequiredArgsConstructor
public class ShopRepository {

  private final EntityManager entityManager;

  public Shop create(final Shop shop) {
    entityManager.persist(shop);
    return shop;
  }

  public Optional<Shop> findById(final Long id) {
    return Optional.ofNullable(entityManager.find(Shop.class, id));
  }

  public Shop update(final Shop shop) {
    entityManager.merge(shop);
    return shop;
  }

  public void deleteById(final Long id) {
    entityManager.createQuery("DELETE FROM shops s WHERE s.id=:dupa")
        .setParameter("dupa", id)
        .executeUpdate();
  }

  public List<Shop> getAll() {
    return entityManager.createQuery("SELECT s FROM shops s", Shop.class)
        .getResultList();
  }

  public Optional<Shop> findByNameAndAddress(final String name,
                                             final String address) {
    final List<Shop> shops = entityManager.createQuery("SELECT s FROM shops s WHERE s.name=:name AND s.address=:address", Shop.class)
        .setParameter("name", name)
        .setParameter("address", address)
        .setMaxResults(1)
        .getResultList();
    if (shops.size() == 1) {
      return Optional.of(shops.get(0));
    }
    return Optional.empty();
  }

}
