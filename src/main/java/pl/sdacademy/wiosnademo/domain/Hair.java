package pl.sdacademy.wiosnademo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hair {

  @Column(name = "has_hair")
  private Boolean has;

  @Column(name = "hair_length")
  private Long length;

  @Column(name = "hair_color")
  private String color;
}
