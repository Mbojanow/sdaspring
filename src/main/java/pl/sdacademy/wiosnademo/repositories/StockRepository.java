package pl.sdacademy.wiosnademo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import pl.sdacademy.wiosnademo.domain.Stock;

public interface StockRepository extends CrudRepository<Stock, Long> {
  List<Stock> findAllByQuantityGreaterThan(Long lowerBound);
}
