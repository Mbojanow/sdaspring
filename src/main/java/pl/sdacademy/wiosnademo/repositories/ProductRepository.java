package pl.sdacademy.wiosnademo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import pl.sdacademy.wiosnademo.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
  List<Product> findAllByNameAndPriceBetween(String name, Double lowerBound, Double upperBound);
  List<Product> findAllByDescriptionContaining(String desc);
}
