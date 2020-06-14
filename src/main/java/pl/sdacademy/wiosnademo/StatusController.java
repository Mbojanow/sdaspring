package pl.sdacademy.wiosnademo;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
public class StatusController {
  @GetMapping
  public Status fetchStatus() {
    return new Status("UP");
  }

  @PostMapping
  public void saveStatus() throws IOException {
    Runtime.getRuntime().exec("mkdir -p /tmp/statuses && echo UP >> /tmp/statuses/status");
  }
}
