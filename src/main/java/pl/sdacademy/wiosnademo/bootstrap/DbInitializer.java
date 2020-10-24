package pl.sdacademy.wiosnademo.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import pl.sdacademy.wiosnademo.domain.User;
import pl.sdacademy.wiosnademo.model.UserStatus;
import pl.sdacademy.wiosnademo.repositories.UserRepository;

@Component
@RequiredArgsConstructor
@Transactional
public class DbInitializer {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @EventListener(ContextRefreshedEvent.class)
  public void onEvent() {
    userRepository.save(new User("test@gmail.com",
        passwordEncoder.encode("PWD_123"), UserStatus.ACTIVATED));
  }
}
