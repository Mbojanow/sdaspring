package ok.sdacademy.mappers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserController {

  private final UserService userService;

  @GetMapping("/api/users/{id}")
  public UserDto findUser(@PathVariable Long id) {
    return userService.findUserById(id);
  }
}
