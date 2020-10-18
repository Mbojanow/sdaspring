package pl.sdacademy.wiosnademo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import pl.sdacademy.wiosnademo.domain.Shop;
import pl.sdacademy.wiosnademo.exceptions.SdaException;
import pl.sdacademy.wiosnademo.repositories.ShopJpaRepository;
import pl.sdacademy.wiosnademo.repositories.ShopRepository;
import pl.sdacademy.wiosnademo.services.validation.ShopValidator;

@Transactional
@RequiredArgsConstructor
@Service
public class ShopCrudService {

  private final ShopJpaRepository shopJpaRepository;
  private final ShopValidator shopValidator;

  public Shop findById(final Long id) {
    return shopJpaRepository.findById(id)
        .orElseThrow(() ->
            new SdaException(String.format("Shop with given id: %d does not exist", id)));
  }

  public List<Shop> getAllShops() {
    return shopJpaRepository.findAll();
  }

  public Shop createShop(final Shop shop) {
    shopValidator.validate(shop);
    shop.setId(null);
    return shopJpaRepository.save(shop);
  }

  public void update(final Shop updatedShop, final Long id) {
    final Shop existingShop = findById(id);
    shopValidator.validateUpdate(existingShop, updatedShop);
    existingShop.setAddress(updatedShop.getAddress());
    existingShop.setArea(updatedShop.getArea());
    existingShop.setName(updatedShop.getName());
    existingShop.setPhoneNumber(updatedShop.getPhoneNumber());
    shopJpaRepository.save(existingShop);
  }

  public void updatePartially(final Shop updatedShop, final Long id) {
    final Shop existingShop = findById(id);
    shopValidator.validateUpdate(existingShop, updatedShop);
    if (updatedShop.getAddress() != null) {
      existingShop.setAddress(updatedShop.getAddress());
    }

    if (updatedShop.getArea() != null) {
      existingShop.setArea(updatedShop.getArea());
    }

    if (updatedShop.getName() != null) {
      existingShop.setName(updatedShop.getName());
    }

    if (updatedShop.getPhoneNumber() != null) {
      existingShop.setPhoneNumber(updatedShop.getPhoneNumber());
    }
    shopJpaRepository.save(existingShop);
  }

  public void removeShop(final Long id) {
    findById(id);
    shopJpaRepository.deleteById(id);
  }
}
