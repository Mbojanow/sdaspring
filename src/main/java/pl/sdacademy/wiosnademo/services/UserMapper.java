package pl.sdacademy.wiosnademo.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import pl.sdacademy.wiosnademo.domain.User;
import pl.sdacademy.wiosnademo.model.UserForm;
import pl.sdacademy.wiosnademo.model.UserStatus;

@Component
@RequiredArgsConstructor
public class UserMapper {

  private final PasswordEncoder passwordEncoder;

  public User toUser(final UserForm userForm) {
    // lepiej wykorzystać Builder
    final User user = new User();
    user.setEmail(userForm.getEmail());
    user.setPassword(passwordEncoder.encode(userForm.getPassword()));
    user.setStatus(UserStatus.ACTIVATED);
    return user;
  }
}
