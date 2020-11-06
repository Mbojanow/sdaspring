package pl.sdacademy.wiosnademo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

@Component
@RequiredArgsConstructor
@Transactional
public class UserValidator {

  private final UserRepository userRepository;

  public void validateUser(final User user, final Errors errors) {
    userRepository.findByEmail(user.getEmail()).ifPresent(usr -> {
      errors.rejectValue("email", "user.email.error");
    });
  }
}
