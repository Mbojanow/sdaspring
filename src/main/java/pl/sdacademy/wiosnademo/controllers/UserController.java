package pl.sdacademy.wiosnademo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sdacademy.wiosnademo.Status;
import pl.sdacademy.wiosnademo.User;
import pl.sdacademy.wiosnademo.UserForm;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final List<User> users = new ArrayList<>();

    public UserController() {
        users.add(new User("Andrzej", "andrzej@gmail.com", "Andrzej_123", Status.ACTIVE));
        users.add(new User("Ala", "ala@gmail.com", "Andrzej_123", Status.ACTIVE));
        users.add(new User("Wojtek", "wojtek@gmail.com", "Andrzej_123", Status.ACTIVE));
    }

    @GetMapping
    public String showUsersList(final ModelMap modelMap) {
        modelMap.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/create")
    public String showCreateForm(final ModelMap modelMap) {
        modelMap.addAttribute("userForm", new UserForm());
        return "users-create";
    }

    @PostMapping("/create")
    public String handleCreateForm(@ModelAttribute("userForm") final UserForm userForm, ModelMap modelMap) {
        users.add(new User(userForm.getUsername(), userForm.getEmail(), userForm.getPassword(), Status.ACTIVE));
        return "redirect:/users";
    }
}
