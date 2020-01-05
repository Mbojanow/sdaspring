package pl.sdacademy.wiosnademo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping(path = "/index")
    public String getIndex(final ModelMap modelMap) {
        modelMap.addAttribute("btnText", "Click ME!");
        return "index";
    }
}
