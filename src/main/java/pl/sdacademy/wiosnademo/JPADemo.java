package pl.sdacademy.wiosnademo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pl.sdacademy.wiosnademo.domain.Toy;
import pl.sdacademy.wiosnademo.repositories.ToyRepository;

@Component
public class JPADemo implements CommandLineRunner {

  private final ToyRepository toyRepository;

  public JPADemo(final ToyRepository toyRepository) {
    this.toyRepository = toyRepository;
  }

  @Override
  @Transactional
  public void run(final String... args) throws Exception {
    toyRepository.save(new Toy(null, "resorak", "plastik", "hot wheels", 11L));

    final List<Toy> all = toyRepository.findAll();
    System.out.println(toyRepository.findById(1L));

    toyRepository.findAllByProducer("hot wheels").forEach(System.out::println);
  }
}
