package pl.sdacademy.wiosnademo.services.validation;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import pl.sdacademy.wiosnademo.domain.Shop;
import pl.sdacademy.wiosnademo.exceptions.SdaException;
import pl.sdacademy.wiosnademo.repositories.ShopJpaRepository;
import pl.sdacademy.wiosnademo.repositories.ShopRepository;

@Component
@RequiredArgsConstructor
public class ShopValidator {

  private final ShopJpaRepository shopJpaRepository;

  public void validate(final Shop shop) {
    shopJpaRepository.findShopWhereNameAndAddressHaveValues(shop.getName(), shop.getAddress())
        .ifPresent(s -> { throw new SdaException(String.format(
            "Shop with name %s at address %s already exists", shop.getName(), shop.getAddress()));});
  }

  public void validateUpdate(final Shop existingShop, final Shop updatedShop) {
    if (nameNotEquals(existingShop, updatedShop) || addressNotEquals(existingShop, updatedShop)) {
      validate(updatedShop);
    }
  }

  private boolean nameNotEquals(final Shop existingShop, final Shop updatedShop) {
    return updatedShop.getName() != null && !existingShop.getName().equals(updatedShop.getName());
  }

  private boolean addressNotEquals(final Shop existingShop, final Shop updatedShop) {
    return updatedShop.getAddress() != null && !existingShop.getAddress().equals(updatedShop.getAddress());
  }
}
