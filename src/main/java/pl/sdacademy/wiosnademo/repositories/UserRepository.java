package pl.sdacademy.wiosnademo.repositories;


import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.sdacademy.wiosnademo.domain.User;

// NIE dodajemy adotacji @Repository
public interface UserRepository extends JpaRepository<User, String> {
  // 1. szukanie po mailu (mail jest unikalny)
  Optional<User> findByEmail(String email);

  // 2. szukanie ma nr telefonu (nie jest unikalny)
  // 123 123 123 -> 123 123 123 lub +48 123 123 123
  // Set nieco szybszy niż List
  Set<User> findAllByPhoneNumberEndsWith(String phoneNumber);

  // 3. szukamy po nr telefonu i emailu
  Optional<User> findByEmailAndPhoneNumberEndsWith(String email, String phoneNumber);

  // 4. szukamy po nr telefonu lub email
  Set<User> findAllByEmailOrPhoneNumberEndsWith(String email, String phoneNumber);

  Optional<User> findByUsernameOrEmail(String username, String email);
}
