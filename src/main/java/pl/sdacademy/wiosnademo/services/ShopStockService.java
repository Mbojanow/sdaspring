package pl.sdacademy.wiosnademo.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import pl.sdacademy.wiosnademo.domain.Product;
import pl.sdacademy.wiosnademo.domain.Shop;
import pl.sdacademy.wiosnademo.domain.Stock;
import pl.sdacademy.wiosnademo.model.StockQuantity;
import pl.sdacademy.wiosnademo.repositories.StockRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class ShopStockService {

  private final ShopCrudService shopCrudService;
  private final ProductService productService;
  private final StockRepository stockRepository;

  public void addProductStockToShop(final Long shopId,
                                    final Long productId,
                                    final StockQuantity stockQuantity) {
    final Shop shop = shopCrudService.findById(shopId);
    final Product product = productService.findById(productId);
    final Optional<Stock> existingStock = stockRepository.findAllByQuantityGreaterThan(0L).stream()
        // napisać zapytanie specyficzne dla zadania
        // SELECT * FROM stocks s WHERE s.productId=:productId AND s.shopId=:shopId // powinno zwraca Optional
        .filter(stock -> stock.getProduct().getId().equals(productId)
            && stock.getShop().getId().equals(shopId))
        .findFirst();

    if (existingStock.isEmpty()) {
      final Stock stock = new Stock(null, stockQuantity.getQuantity(), shop, product);
      stockRepository.save(stock);
      shop.getStocks().add(stock);
      product.getStocks().add(stock);
    } else {
      final Stock stock = existingStock.get();
      stock.setQuantity(stock.getQuantity() + stockQuantity.getQuantity());
      stockRepository.save(stock); // nie jest wymagane - encja w stanie managed
    }
  }

}
