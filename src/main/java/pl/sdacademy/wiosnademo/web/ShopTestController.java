package pl.sdacademy.wiosnademo.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pl.sdacademy.wiosnademo.domain.Shop;
import pl.sdacademy.wiosnademo.repositories.ShopJpaRepository;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class ShopTestController {

  // NIE W PRAWDZIWYM KODZIE
  private final ShopJpaRepository shopJpaRepository;

  @GetMapping
  public List<Shop> getShops() {
    return shopJpaRepository.findAllShopsWhichProductIsOnStock(1L);
  }
}
