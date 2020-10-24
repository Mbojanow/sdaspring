package pl.sdacademy.wiosnademo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import pl.sdacademy.wiosnademo.services.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  @GetMapping
  public String showUsersView(final ModelMap modelMap) {
    modelMap.addAttribute("users", userService.getAllUsers());
    return "users";
  }

}
