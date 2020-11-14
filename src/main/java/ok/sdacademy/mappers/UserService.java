package ok.sdacademy.mappers;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public UserDto findUserById(final Long id) {
    final User user = userRepository.findById(id).orElseThrow();
    return userMapper.toUserDto(user);
  }
}
