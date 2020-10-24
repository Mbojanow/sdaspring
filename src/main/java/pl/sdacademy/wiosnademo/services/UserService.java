package pl.sdacademy.wiosnademo.services;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import pl.sdacademy.wiosnademo.domain.User;
import pl.sdacademy.wiosnademo.model.UserForm;
import pl.sdacademy.wiosnademo.repositories.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public void createUser(final UserForm userForm) {
    final User user = userMapper.toUser(userForm);
    userRepository.save(user);
  }

  public User findUserByEmail(final String email) {
    return userRepository.findById(email)
        .orElseThrow(); // lepiej wyrzucic wyjatek wlasnego typu
  }
}
