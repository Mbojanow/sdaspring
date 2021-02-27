package pl.sdacademy.wiosnademo.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.sdacademy.wiosnademo.domain.User;
import pl.sdacademy.wiosnademo.repositories.UserRepository;

@Service
@Transactional
public class UserCrudService {

  private final UserRepository userRepository;

  public UserCrudService(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  // stworzenie użytkownika
  public User createUser(User user) {
    userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail())
        .ifPresent(existingUser -> { throw new ApplicationException("User with requested username or email already exists"); });
    return userRepository.save(user);
  }

  // aktualizacja
  public void updateUser(String username, User user) {
    final User existingUser = getUserByUsername(username);
    existingUser.setEmail(user.getEmail());
    existingUser.setPhoneNumber(user.getPhoneNumber());
    existingUser.setPassword(user.getPassword());

    // userRepository.save(existingUser); // persist -> zapisz, merge -> aktualizuj
  }

  // usuwanie
  public void deleteUser(String username) {
    // opcjonalnie sprawdzic czy uzytkownik istnieje
    getUserByUsername(username);
    userRepository.deleteById(username);
  }

  public User getUserByUsername(String username) {
    final Optional<User> existingUserOptional = userRepository.findById(username);
    if (existingUserOptional.isEmpty()) {
      throw new ApplicationException("User with username " + username + " does not exist");
    }
    return existingUserOptional.get();
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public Set<User> search(final String email, final String mobile, final String type) {
    if (type.equals("or")) {
      return userRepository.findAllByEmailOrPhoneNumberEndsWith(email, mobile);
    } else if (type.equals("and")) {
      return userRepository.findByEmailAndPhoneNumberEndsWith(email, mobile)
          .map(Set::of)
          .orElse(Set.of());
    }
    throw new ApplicationException("Search type not found");
  }
}
