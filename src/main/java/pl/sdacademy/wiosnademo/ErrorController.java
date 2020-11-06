package pl.sdacademy.wiosnademo;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController extends AbstractErrorController {

  public ErrorController(final ErrorAttributes errorAttributes) {
    super(errorAttributes);
  }

  @Override
  public String getErrorPath() {
    return "/error";
  }

  @RequestMapping("/error")
  public String handlerError(ModelMap modelMap) {
    return "error";
  }
}
