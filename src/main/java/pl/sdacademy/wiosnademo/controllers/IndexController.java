package pl.sdacademy.wiosnademo.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.sdacademy.wiosnademo.model.NameForm;

@Controller
@RequestMapping("/index")
public class IndexController {

  private static final String MODEL_ATTR_BUTTON_KEY = "btnText";
  private static final String MODEL_ATTR_NAME_FORM = "nameForm";

  @GetMapping
  public String showIndexPage(final ModelMap modelMap) {
    modelMap.addAttribute(MODEL_ATTR_BUTTON_KEY, "Click me!");
    modelMap.addAttribute(MODEL_ATTR_NAME_FORM, new NameForm());
    return "index";
  }

  @PostMapping
  public String handleNameSave(@Valid @ModelAttribute(name = MODEL_ATTR_NAME_FORM) final NameForm nameForm, final ModelMap modelMap) {
    //final NameForm attribute = (NameForm)modelMap.getAttribute(MODEL_ATTR_NAME_FORM);
    modelMap.addAttribute("fn", nameForm.getFirstName());
    modelMap.addAttribute("ln", nameForm.getLastName());
    return showIndexPage(modelMap);
  }
}
