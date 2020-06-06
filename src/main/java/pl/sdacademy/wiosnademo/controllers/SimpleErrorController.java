package pl.sdacademy.wiosnademo.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleErrorController extends AbstractErrorController {

  public SimpleErrorController(final ErrorAttributes errorAttributes) {
    super(errorAttributes);
  }

  @RequestMapping("/error")
  public ResponseEntity<Object> handleError(final HttpServletRequest request, final HttpServletResponse response,
                                            final HttpMethod httpMethod) {
    final Map<String, Object> errorAttributes = getErrorAttributes(request, ErrorAttributeOptions.defaults());
    errorAttributes.put("httpMethod", httpMethod);
    return ResponseEntity.status(response.getStatus()).body(errorAttributes);
  }

  @Override
  public String getErrorPath() {
    return "dahiudqhuerq";
  }
}
