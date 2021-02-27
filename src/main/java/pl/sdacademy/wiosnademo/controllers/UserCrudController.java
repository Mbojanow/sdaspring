package pl.sdacademy.wiosnademo.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pl.sdacademy.wiosnademo.domain.User;
import pl.sdacademy.wiosnademo.services.UserCrudService;

/*
 GET /users
 GET /users/ID
 POST /users
 PUT /users/ID
 DELETE /users/ID
 */

@RestController // == @Controller + @ResponseBody
//@Controller
@RequestMapping("/api/users")
public class UserCrudController {

  private final UserCrudService userCrudService;

  public UserCrudController(final UserCrudService userCrudService) {
    this.userCrudService = userCrudService;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE) // filtrowanie
  public List<User> getAllUsers() {
    return userCrudService.getAllUsers();
  }

  @GetMapping("/{username}")
  public User getUser(@PathVariable(name = "username") String username) {
    return userCrudService.getUserByUsername(username);
  }

  @PostMapping
  public ResponseEntity<Void> createUser(@Valid @RequestBody User user) throws URISyntaxException { // @RequestBody -> konwertuje ciało żadania na obiektu javowy (JSON -> User)
    userCrudService.createUser(user);
    return ResponseEntity.created(new URI("/api/users/" + user.getUsername())).build();
  }

  @PutMapping("/{username}") // /{p1}/{p2}/{p3}
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void updateUser(@PathVariable(name = "username") String username, @Valid @RequestBody User user) {
    userCrudService.updateUser(username, user);
  }

  @DeleteMapping("/{username}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteUser(@PathVariable String username) {
    userCrudService.deleteUser(username);
  }

//  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//  public ResponseEntity<List<User>> getAllUsers() {
//    return ResponseEntity.ok(userCrudService.getAllUsers());
//  }

  // http://localhost:8080/api/users/search?email=wartosc&mobile=wartosc
  @GetMapping("/search")
  public Set<User> findUsers(@RequestParam(name = "email") String email, @RequestParam(name = "mobile") String mobile,
                             @RequestParam(name = "type") String type) { // or lub and
    return userCrudService.search(email, mobile, type);
  }
}
