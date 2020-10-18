package pl.sdacademy.wiosnademo.bootstrap;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import pl.sdacademy.wiosnademo.domain.Shop;
import pl.sdacademy.wiosnademo.repositories.ShopRepository;

@Profile("dev")
@Component
@RequiredArgsConstructor
public class DbInitializer implements CommandLineRunner {

  private final ShopRepository shopRepository;

  @Transactional
  @Override
  public void run(final String... args) throws Exception {
    final Shop shopA = new Shop(null, "Biedra", "Czechosłowacka 3",
        300, "123-123-321", List.of());
    final Shop shopB = new Shop(null, "On the Wok", "Somewhere 1",
        100, "728-123-321", List.of());
    shopRepository.create(shopA);
    shopRepository.create(shopB);
  }
}
