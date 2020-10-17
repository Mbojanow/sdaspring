package pl.sdacademy.wiosnademo.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import pl.sdacademy.wiosnademo.domain.Shop;
import pl.sdacademy.wiosnademo.exceptions.SdaException;
import pl.sdacademy.wiosnademo.repositories.ShopRepository;

@Transactional
@RequiredArgsConstructor
@Service
public class ShopCrudService {

  private final ShopRepository shopRepository;

  public Shop findById(final Long id) {
    return shopRepository.findById(id)
        .orElseThrow(() ->
            new SdaException(String.format("Shop with given id: %d does not exist", id)));
  }

  public List<Shop> getAllShops() {
    return shopRepository.getAll();
  }

  public Shop createShop(final Shop shop) {
    shop.setId(null);
    return shopRepository.create(shop);
  }

  public void update(final Shop updatedShop, final Long id) {
    final Shop existingShop = findById(id);
    existingShop.setAddress(updatedShop.getAddress());
    existingShop.setArea(updatedShop.getArea());
    existingShop.setName(updatedShop.getName());
    existingShop.setPhoneNumber(updatedShop.getPhoneNumber());
    shopRepository.update(existingShop);
  }

  public void updatePartially(final Shop updatedShop, final Long id) {
    final Shop existingShop = findById(id);
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
    shopRepository.update(existingShop);
  }

  public void removeShop(final Long id) {
    findById(id);
    shopRepository.deleteById(id);
  }
}
