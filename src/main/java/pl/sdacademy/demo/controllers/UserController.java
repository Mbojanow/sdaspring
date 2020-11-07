package pl.sdacademy.demo.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import pl.sdacademy.demo.entities.User;
import pl.sdacademy.demo.repositories.UserRepository;
import pl.sdacademy.demo.services.UserService;
import pl.sdacademy.demo.services.UserValidator;

@RequiredArgsConstructor
@Controller
public class UserController {

  private final UserService userService;
  private final UserRepository userRepository;
  private final UserValidator userValidator;

  @GetMapping("/users")
  public String showUsers(final ModelMap modelMap) {
    modelMap.addAttribute("user", new User()); // User - reprezentacja formularza
    modelMap.addAttribute("users", userRepository.findAll());
    return "users";
  }

  @PostMapping("/users")
  public String createUser(@Valid @ModelAttribute(name = "user") final User userToCreate, final Errors errors) {
    //userValidator.validateUser(userToCreate, errors);
    if (errors.hasErrors()) {
      return "users";
    }
    //if (1 == 1) throw new RuntimeException("Sth went wrong");//return "redirect:/error";
    userService.createUser(userToCreate);
    return "redirect:/users"; // powrót na GETa
  }
}
