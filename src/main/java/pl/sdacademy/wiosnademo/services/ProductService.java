package pl.sdacademy.wiosnademo.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import pl.sdacademy.wiosnademo.domain.Product;
import pl.sdacademy.wiosnademo.exceptions.SdaException;
import pl.sdacademy.wiosnademo.repositories.ProductRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

  // A int i @Component - B implements A -> możemy wstrzykiwać A

  private final ProductRepository productRepository;

  public Product createProduct(final Product product) {
    return productRepository.save(product);
  }

  public Product findById(final Long id) {
    return productRepository.findById(id)
        .orElseThrow(() -> new SdaException("Products with id " + id + " does not exist"));
  }
}
