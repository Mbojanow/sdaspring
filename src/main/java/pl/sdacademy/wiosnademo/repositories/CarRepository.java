package pl.sdacademy.wiosnademo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.sdacademy.wiosnademo.domain.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
}
