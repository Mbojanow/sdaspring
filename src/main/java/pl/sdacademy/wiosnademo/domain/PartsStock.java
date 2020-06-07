package pl.sdacademy.wiosnademo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CollectionId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "parts_stock")
public class PartsStock {

  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "part_name")
  private String name;

  @Column(name = "on_stock")
  private Long stock;
}
