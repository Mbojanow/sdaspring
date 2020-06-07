package pl.sdacademy.wiosnademo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.sdacademy.wiosnademo.domain.MessagesCounter;

public interface MessagesCounterRepository extends JpaRepository<MessagesCounter, Long> {
}
