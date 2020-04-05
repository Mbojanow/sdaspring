package pl.sdacademy.wiosnademo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.sdacademy.wiosnademo.domain.NameForm;

@Controller
@RequestMapping(path = "/index")
public class IndexController {

  @GetMapping
  public String showHomePage(final ModelMap modelMap) {
    modelMap.addAttribute("btnText", "Click ME!");
    modelMap.addAttribute("nameForm", new NameForm());
    return "index";
  }

  @PostMapping
  public String handleNameChange(@ModelAttribute(name = "nameForm") final NameForm nameForm,
                                 final ModelMap modelMap) {
    modelMap.addAttribute("fullName", nameForm.getFirstName() + " " + nameForm.getLastName());
    return showHomePage(modelMap);
  }
}
