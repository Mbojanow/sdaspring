package pl.sdacademy.wiosnademo;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

  public UserDto findUserById(final Long id) {
    final User user = userRepository.findById(id).orElseThrow();
    return userMapper.toUserDto(user);
  }

  public Page<UserDto> getPageOfUsers(final Integer pageNum, final Integer pageSize) {
    final Page<User> page = userRepository.findAll(PageRequest.of(pageNum, pageSize,
        Sort.by("email").descending()));
    return page.map(userMapper::toUserDto);
  }

}
