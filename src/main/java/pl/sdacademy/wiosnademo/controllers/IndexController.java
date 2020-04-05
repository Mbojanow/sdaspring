package pl.sdacademy.wiosnademo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/index")
public class IndexController {

  @GetMapping
  public String showHomePage() {
    return "index";
  }
}
