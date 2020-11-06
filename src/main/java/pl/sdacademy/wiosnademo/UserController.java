package pl.sdacademy.wiosnademo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserController {

  private final UserValidator userValidator;
  private final UserService userService;

  @GetMapping("/users")
  public String showUserForm(ModelMap modelMap) {
    modelMap.addAttribute("user", new User());
    return "users";
  }

  @PostMapping("/users")
  public String createUser(@Valid @ModelAttribute("user") User user, Errors errors) {
    userValidator.validateUser(user, errors);
    if (errors.hasErrors()) {
      return "users";
    }
    userService.createUser(user);
    //if (1 == 1) throw new RuntimeException("BOOM");
    return "redirect:/users";
  }
}
