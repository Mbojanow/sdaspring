package pl.sdacademy.wiosnademo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import pl.sdacademy.wiosnademo.domain.Shop;

public interface ShopJpaRepository extends CrudRepository<Shop, Long> {
  // analogiczne do entityManager.createQuery(SELECT s FROM shops s WHERE s.name=:name, SHop.class).getResultList()
  List<Shop> findAllByName(String name);
}
