package pl.sdacademy.wiosnademo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.sdacademy.wiosnademo.domain.Manufacturer;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
}
