package pl.sdacademy.wiosnademo.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "producers")
public class Manufacturer {

  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "producer_name")
  private String name;

  @Column(name = "country")
  private String country;

  @OneToMany
  @JoinColumn(name = "producer_id")
  private List<Car> cars;
}
