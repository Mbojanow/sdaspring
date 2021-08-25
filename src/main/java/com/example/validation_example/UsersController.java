package com.example.validation_example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UsersController {

    @GetMapping("/users")
    public String showUsers(ModelMap modelMap) { //Model, ModelAndView
        modelMap.addAttribute("backendKey", "ThisIsMyValue");
        modelMap.addAttribute("users", List.of(
                new User(1L, "test1@test.com", "Adam"),
                new User(2L, "test2@test.com", "Ala")
        ));
        modelMap.addAttribute("userForm", new UserForm());
        return "users";
    }

    @PostMapping("/users/create")
    public String createUser(@Valid @ModelAttribute("userForm") UserForm userForm,
                             BindingResult errors, ModelMap modelMap) {
        //obsłużyć stworzenie usera
        if (errors.hasErrors()) {
            modelMap.addAttribute("backendKey", "ThisIsMyValue");
            modelMap.addAttribute("users", List.of(
                    new User(1L, "test1@test.com", "Adam"),
                    new User(2L, "test2@test.com", "Ala")
            ));
            modelMap.addAttribute("userForm", userForm);
            return "users";
        }
        return "redirect:/users";
    }
}
