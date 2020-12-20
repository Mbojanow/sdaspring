package pl.sdacademy.wiosnademo;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import pl.sdacademy.wiosnademo.domain.User;
import pl.sdacademy.wiosnademo.model.UserDto;
import pl.sdacademy.wiosnademo.model.mappers.UserMapper;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

  private final UserMapper userMapper;
  private final UserRepository userRepository;

  public UserDto createUser(final UserDto userDto) {
    final User userToCreate = userMapper.toUser(userDto);
    userToCreate.setPassword(UUID.randomUUID().toString());
    return userMapper.toUserDto(userRepository.save(userToCreate));
  }


}
