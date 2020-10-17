package pl.sdacademy.wiosnademo.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pl.sdacademy.wiosnademo.domain.Shop;
import pl.sdacademy.wiosnademo.model.Shops;
import pl.sdacademy.wiosnademo.services.ShopCrudService;

@RestController // @Controller + @ResponseBody
@RequestMapping("/api/shops")
@RequiredArgsConstructor
public class ShopController {

  private final ShopCrudService shopCrudService;

  @GetMapping("/{id}")
  public Shop getShop(@PathVariable(name = "id") final Long idx) { // @ResponseBody w @RestController -> ResponseEntity<Shop>
    return shopCrudService.findById(idx);
  }

  @GetMapping
  public Shops getAllShops() {
    return new Shops(shopCrudService.getAllShops());
  }

}
