package pl.sdacademy.wiosnademo.bootstrap;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import pl.sdacademy.wiosnademo.domain.Product;
import pl.sdacademy.wiosnademo.domain.Shop;
import pl.sdacademy.wiosnademo.domain.Stock;
import pl.sdacademy.wiosnademo.repositories.ProductRepository;
import pl.sdacademy.wiosnademo.repositories.ShopJpaRepository;
import pl.sdacademy.wiosnademo.repositories.ShopRepository;
import pl.sdacademy.wiosnademo.repositories.StockRepository;

@Profile("dev")
@Component
@RequiredArgsConstructor
public class DbInitializer implements CommandLineRunner {

  private final ShopJpaRepository shopJpaRepository;
  private final StockRepository stockRepository;
  private final ProductRepository productRepository;

  @Transactional
  @Override
  public void run(final String... args) throws Exception {
    final Product product = new Product(null,
        "Onions", "Polish ONIONS", "PROLANG", 1.99,
        new ArrayList<>());
    productRepository.save(product);
    final Stock stock = new Stock(null, 3L, null, product);
    stockRepository.save(stock);

    final Shop shopA = new Shop(null, "Biedra", "Czechosłowacka 3",
        300, "123-123-321", List.of(stock));
    final Shop shopB = new Shop(null, "On the Wok", "Somewhere 1",
        100, "728-123-321", List.of());
    shopJpaRepository.save(shopA);
    shopJpaRepository.save(shopB);
  }
}
