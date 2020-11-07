package pl.sdacademy.demo.repositories;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pl.sdacademy.demo.entities.User;
import pl.sdacademy.demo.entities.UserType;

public interface UserRepository extends JpaRepository<User, String> {

  Optional<User> findByEmail(String email);

  Set<User> findAllByType(UserType type);
}
