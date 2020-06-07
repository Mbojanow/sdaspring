package pl.sdacademy.wiosnademo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "cars")
public class Car {

  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "model_name")
  private String name;

  @ManyToOne
  private Manufacturer manufacturer;
}
