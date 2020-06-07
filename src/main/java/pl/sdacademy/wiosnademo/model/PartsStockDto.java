package pl.sdacademy.wiosnademo.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartsStockDto implements Serializable {
  private String name;
  private Long stock;
}
