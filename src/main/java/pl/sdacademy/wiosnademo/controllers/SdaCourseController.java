package pl.sdacademy.wiosnademo.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.sdacademy.wiosnademo.model.SdaCourse;

@RestController
@RequestMapping(value = "/api/v1/courses", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_HTML_VALUE })
public class SdaCourseController {

  private static final List<SdaCourse> COURSES = List.of(
      SdaCourse.builder()
          .id(1L)
          .name("Tester manualny")
          .description("manual tester desc")
          .price(999)
          .build(),
      SdaCourse.builder()
          .name("Java Course")
          .description("Java rules")
          .price(1999)
          .id(2L)
          .build());

  @RequestMapping(method = RequestMethod.GET)
  public List<SdaCourse> getCourses() {
    return COURSES;
  }

  @RequestMapping(path = "/{id}", method = RequestMethod.GET)
  public SdaCourse getCourse(@PathVariable final Long id) {
    return COURSES.stream()
        .filter(course -> course.getId().equals(id))
        .findFirst()
        .orElseThrow();
  }

  @GetMapping("/search")
  public SdaCourse findByNameAndPrice(@RequestParam(name = "name") final String name,
                                      @RequestParam(name = "price", required = false) final Optional<Integer> price) {
    return COURSES.stream()
        .filter(course -> course.getName().equals(name))
        .filter(course -> price.map(value -> course.getPrice().equals(value)).orElse(true))
        .findFirst()
        .orElseThrow();
  }

  @GetMapping("/search2/{searchParams}")
  public SdaCourse findByNameAndPrice(@MatrixVariable final Map<String, String> searchParams) {
    final String name = searchParams.get("name");
    final Integer price = Integer.valueOf(searchParams.get("price"));

    return COURSES.stream()
        .filter(course -> course.getName().equals(name))
        .filter(course -> course.getPrice().equals(price))
        .findFirst()
        .orElseThrow();
  }
}

























