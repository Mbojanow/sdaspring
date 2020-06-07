package pl.sdacademy.wiosnademo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.sdacademy.wiosnademo.domain.PartsStock;

public interface PartsStockRepository extends JpaRepository<PartsStock, Long> {
  Optional<PartsStock> findByName(String name);
}
