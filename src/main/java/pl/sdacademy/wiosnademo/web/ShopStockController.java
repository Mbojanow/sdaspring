package pl.sdacademy.wiosnademo.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pl.sdacademy.wiosnademo.model.StockQuantity;
import pl.sdacademy.wiosnademo.services.ShopStockService;

@RequestMapping("/api/v1/shops")
@RequiredArgsConstructor
@RestController
public class ShopStockController {

  private  final ShopStockService shopStockService;

  // dodanie na stan sklepu konkretna ilosc produktu
  @PostMapping("/{id}/stocks/products/{productId}")
  @ResponseStatus(HttpStatus.CREATED)
  public void addProductsToShopsStock(@PathVariable(name = "id") final Long shopId,
                                      @PathVariable final Long productId,
                                      @RequestBody final StockQuantity stockQuantity) {
    shopStockService.addProductStockToShop(shopId, productId, stockQuantity);
  }
}
