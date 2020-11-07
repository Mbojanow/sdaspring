package pl.sdacademy.demo.services;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

import lombok.RequiredArgsConstructor;
import pl.sdacademy.demo.entities.User;
import pl.sdacademy.demo.repositories.UserRepository;

@Component
@RequiredArgsConstructor
public class UserValidator {

  private final UserRepository userRepository;

  @Transactional
  public void validateUser(final User user, final Errors errors) {
    final Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
    if (existingUser.isPresent()) {
      errors.rejectValue("email", "user.email.unique.error"); // email -> POLE w formularzu na frontendzie
    }
  }
}
