package pl.sdacademy.demo.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController extends AbstractErrorController {

  public ErrorController(final ErrorAttributes errorAttributes) {
    super(errorAttributes);
  }

  @Override
  public String getErrorPath() {
    return "/COKOLWIEK_TO_NIE_JEST_NIGDY_UZYWANE";
  }

  @RequestMapping(path = "/error") // WAŻNE - obsługujemy wszystkie metody HTTP (zarówno GET i POST)
  public String showErrorPage(ModelMap modelMap, final HttpServletRequest request) {
    return "error";
  }
}
