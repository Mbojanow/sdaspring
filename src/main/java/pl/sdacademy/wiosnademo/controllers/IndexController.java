package pl.sdacademy.wiosnademo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sdacademy.wiosnademo.model.NameForm;

@Controller
public class IndexController {

    @GetMapping(path = "/index")
    public String getIndex(final ModelMap modelMap) {
        modelMap.addAttribute("btnText", "Click ME!");
        modelMap.addAttribute("nameForm", new NameForm());
        return "index";
    }

    @PostMapping(path = "/index")
    public String getIndex(@ModelAttribute final NameForm nameForm, final ModelMap modelMap) {
        modelMap.addAttribute("btnText", "Click ME!");
        modelMap.addAttribute("firstName", nameForm.getFirstName());
        modelMap.addAttribute("lastName", nameForm.getLastName());
        return "index";
    }
}
