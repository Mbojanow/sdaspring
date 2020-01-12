package pl.sdacademy.wiosnademo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.sdacademy.wiosnademo.domain.NameForm;

@Controller
@RequestMapping("/index")
public class IndexController {

  @GetMapping
  public String viewHomePage(final ModelMap modelMap) {
    modelMap.addAttribute("btnText", "Click Me!!!");
    modelMap.addAttribute("nameForm", new NameForm("Insert first name",
        "Insert last name"));
    return "index";
  }

  @PostMapping
  public String handleForm(@ModelAttribute(name = "nameForm") final NameForm nameForm,
                           final ModelMap modelMap) {
    modelMap.addAttribute("fn", nameForm.getFirstName());
    modelMap.addAttribute("ln", nameForm.getLastName());
    return "index";
  }
}
