package pl.sdacademy.wiosnademo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageEntityRepository extends JpaRepository<ImageEntity, Long> {
}
