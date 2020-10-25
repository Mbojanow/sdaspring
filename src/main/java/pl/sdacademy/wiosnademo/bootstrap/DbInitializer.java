package pl.sdacademy.wiosnademo.bootstrap;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import pl.sdacademy.wiosnademo.config.UserProperties;
import pl.sdacademy.wiosnademo.domain.Role;
import pl.sdacademy.wiosnademo.domain.User;
import pl.sdacademy.wiosnademo.model.UserStatus;
import pl.sdacademy.wiosnademo.repositories.UserRepository;

@Component
@RequiredArgsConstructor
@Transactional
public class DbInitializer {

  @Value("${sda.user.email:default@email.com}")
  private String email;

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserProperties userProperties;

  @EventListener(ContextRefreshedEvent.class)
  public void onEvent() {
    userRepository.save(new User(userProperties.getEmail(),
        passwordEncoder.encode(userProperties.getPassword()),
        userProperties.getStatus(),
        List.of(new Role("ROLE_ADMIN", "admin group", true, List.of()))));
  }
}
