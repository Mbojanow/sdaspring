package pl.sdacademy.wiosnademo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "toys")
public class Toy {

  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "name")
  private String name;

  private String material;

  private String producer;

  private Long age;
}
