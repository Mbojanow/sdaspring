package pl.sdacademy.wiosnademo.web;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pl.sdacademy.wiosnademo.annotations.HandledByGlobalExceptionHandler;
import pl.sdacademy.wiosnademo.domain.Shop;
import pl.sdacademy.wiosnademo.model.Shops;
import pl.sdacademy.wiosnademo.services.ShopCrudService;

@HandledByGlobalExceptionHandler
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

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Shop createShop(@RequestBody final Shop shop) {
    return shopCrudService.createShop(shop);
  }

  @PostMapping("/alternative")
  public ResponseEntity<Void> createShopWithLocation(@RequestBody final Shop shop) throws URISyntaxException {
    final Shop createdShop = shopCrudService.createShop(shop);
    return ResponseEntity
        .created(new URI("/api/shops/" + createdShop.getId()))
        .build();
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void updateShop(@RequestBody final Shop shop, @PathVariable final Long id) {
    shopCrudService.update(shop, id);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteShop(@PathVariable final Long id) {
    shopCrudService.removeShop(id);
  }
}
