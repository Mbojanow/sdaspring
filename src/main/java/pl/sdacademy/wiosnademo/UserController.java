package pl.sdacademy.wiosnademo;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pl.sdacademy.wiosnademo.model.UserDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UserDto createUser(@RequestBody final UserDto userDto) {
    return userService.createUser(userDto);
  }

  @PostMapping("/example")
  public ResponseEntity<Void> createUserExample(@RequestBody final UserDto userDto) throws URISyntaxException {
    final UserDto createdUser = userService.createUser(userDto);
    return ResponseEntity.created(new URI("http://localhost:8080/api/users/" + createdUser.getId())).build();
  }

  @GetMapping("/{id}")
  public UserDto getUserById(@PathVariable final Long id) {
    return userService.findUserById(id);
  }
}
