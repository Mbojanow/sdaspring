package pl.sdacademy.wiosnademo.web;

import javax.validation.Valid;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import pl.sdacademy.wiosnademo.model.UserForm;
import pl.sdacademy.wiosnademo.services.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  @Secured("ROLE_ADMIN")
  @GetMapping
  public String showUsersView(final ModelMap modelMap) {
    modelMap.addAttribute("users", userService.getAllUsers());
    modelMap.addAttribute("userForm", new UserForm());
    return "users";
  }

  @PostMapping("/create")
  public String handleUserCreation(@Valid @ModelAttribute(name = "userForm") final UserForm userForm,
                                   final Errors errors) {
    if (errors.hasErrors()) {
      return "users"; // zwracamy tę templatke w ktorej jest źle wypełniony formularz
    }
    userService.createUser(userForm);
    return "redirect:/users";
  }
}
