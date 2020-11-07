package pl.sdacademy.demo.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import pl.sdacademy.demo.entities.User;
import pl.sdacademy.demo.repositories.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

  private final UserRepository userRepository;

  public User createUser(final User user) {
    return userRepository.save(user);
  }
}
