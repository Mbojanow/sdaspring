package pl.sdacademy.wiosnademo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "toys")
public class Toy {

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  private String material;

  private String producer;

  private Long age;
}
