package pl.sdacademy.wiosnademo;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import pl.sdacademy.wiosnademo.domain.Group;
import pl.sdacademy.wiosnademo.domain.User;

@Component
@RequiredArgsConstructor
public class DbInitializer implements CommandLineRunner {

  private final UserRepository userRepository;
  private final GroupRepository groupRepository;

//  @Autowired
//  private EntityManager entityManager;

  @Transactional
  @Override
  public void run(final String... args) throws Exception {
    final Group groupA = new Group(null, "g1");
    final Group groupB = new Group(null, "g2");
    groupRepository.saveAll(List.of(groupA, groupB)); // groupA -> 1, grouB -> 2
    final User user = new User(null, "andrzej", "Andrzej_123", "andrzej@gmail.com",
        Set.of(groupA, groupB));
    userRepository.save(user); // ++2 -> 3

//    entityManager.find();
//    entityManager.persist();
//    entityManager.merge();
//    entityManager.remove();
//    // SQL - Hibernata => HQLa
//    entityManager.createQuery("SELECT u FROM users u WHERE u.username = :username")
//        .getResultList();
  }
}
