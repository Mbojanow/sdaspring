package pl.sdacademy.wiosnademo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.sdacademy.wiosnademo.domain.PartsStock;

public interface PartsStockRepository extends JpaRepository<PartsStock, Long> {
}
