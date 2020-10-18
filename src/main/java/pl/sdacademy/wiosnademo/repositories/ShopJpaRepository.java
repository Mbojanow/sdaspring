package pl.sdacademy.wiosnademo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import pl.sdacademy.wiosnademo.domain.Shop;

public interface ShopJpaRepository extends JpaRepository<Shop, Long> {
  // analogiczne do entityManager.createQuery(SELECT s FROM shops s WHERE s.name=:name, SHop.class).getResultList()
  List<Shop> findAllByName(String name);
  List<Shop> findAllByPhoneNumber(String phoneNumber);
  // SELECT s FROM shops WHERE s.address LIKE ':value%'
  List<Shop> findAllByAddressStartingWith(String streetName);
  List<Shop> findAllByAreaBetween(Integer lowerBound, Integer upperBound);
  List<Shop> findTop3ByPhoneNumberIsNullOrderByNameDesc();

  @Query("SELECT s FROM shops s WHERE s.name=:name AND s.address=:address")
  Optional<Shop> findShopWhereNameAndAddressHaveValues(@Param("name") String name,
                                                       @Param("address") String address);
}
