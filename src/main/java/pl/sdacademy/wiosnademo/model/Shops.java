package pl.sdacademy.wiosnademo.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sdacademy.wiosnademo.domain.Shop;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shops {
  private List<Shop> shops;
}
