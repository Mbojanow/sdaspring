package pl.sdacademy.wiosnademo.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import pl.sdacademy.wiosnademo.domain.Shop;
import pl.sdacademy.wiosnademo.exceptions.SdaException;
import pl.sdacademy.wiosnademo.repositories.ShopJpaRepository;
import pl.sdacademy.wiosnademo.services.validation.ShopValidator;

@ExtendWith(MockitoExtension.class) // daje możliwośc zrozumienia adnotacji @Mock, @InjectMocks przez Junit5
class ShopCrudServiceTest {

  @Mock
  private ShopJpaRepository shopJpaRepository;

  @Mock
  private ShopValidator shopValidator;

  @InjectMocks // new ShopCrudService(shopJpaRepository, shopValidator)
  private ShopCrudService shopCrudService;

  @Test
  void shouldFindById() {
    // given - założenia początkowe i mockowanie zachować mocków
    final Long id = 1L;
    final Shop shop = new Shop(1L, "Biedra",
        "Biedrońskiego 1", 1, "111-111-111", List.of());
    when(shopJpaRepository.findById(id)).thenReturn(Optional.of(shop));

    // when
    final Shop actualShop = shopCrudService.findById(id);

    // then
    assertThat(actualShop).isEqualTo(shop);
  }

  @Test
  void shouldThrowWhenNoShopFoundInDatabase() {
    final Long id = 1L;
    when(shopJpaRepository.findById(id)).thenReturn(Optional.empty());

    assertThatExceptionOfType(SdaException.class)
        .isThrownBy(() -> shopCrudService.findById(id))
        .withMessage("Shop with given id: 1 does not exist");
  }
}