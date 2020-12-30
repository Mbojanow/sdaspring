package pl.sdacademy.wiosnademo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Controller
@RequestMapping("/upload")
@RequiredArgsConstructor
public class UploadController {

  private final ImageEntityRepository imageEntityRepository;

  @GetMapping
  public String showUploadForm(ModelMap modelMap) {
    modelMap.addAttribute("imageForm", new ImageForm());
    imageEntityRepository.findById(1L).ifPresent(imageEntity -> modelMap.addAttribute("myimg", new String(Base64.getEncoder().encode(
        imageEntity.getImage()), StandardCharsets.UTF_8)));
    return "upload";
  }

  @PostMapping
  public String handleImage(@ModelAttribute("imageForm") ImageForm imageForm) throws IOException {
    ImageEntity img = imageEntityRepository.save(new ImageEntity(null, imageForm.getImage().getBytes()));
    return "upload";
  }
}
