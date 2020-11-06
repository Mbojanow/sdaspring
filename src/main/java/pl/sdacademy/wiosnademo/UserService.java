package pl.sdacademy.wiosnademo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public void createUser(final User user) {
    userRepository.save(user);
  }
}
