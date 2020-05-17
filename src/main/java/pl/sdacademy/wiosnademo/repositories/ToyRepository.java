package pl.sdacademy.wiosnademo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.sdacademy.wiosnademo.domain.Toy;

public interface ToyRepository extends JpaRepository<Toy, Long> { // TOY -> typ encji, LONG -> typ ID encji TOY

  // SELECT * FROM Toys WHERE PRODUCER =
  List<Toy> findAllByProducer(String producer);

  // SELECT TOP (1) FROM TOYS WHERE NAME = ? AND AGE = ?
  Optional<Toy> findByNameAndAge(final String name, final Long age);
}
