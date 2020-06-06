package pl.sdacademy.wiosnademo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SdaCourse {
  private Long id;
  private String name;
  private Integer price;
  private String description;
}
