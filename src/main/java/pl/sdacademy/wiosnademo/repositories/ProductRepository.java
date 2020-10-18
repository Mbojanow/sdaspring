package pl.sdacademy.wiosnademo.repositories;

import org.springframework.data.repository.CrudRepository;

import pl.sdacademy.wiosnademo.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
