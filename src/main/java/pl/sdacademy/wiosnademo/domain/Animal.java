package pl.sdacademy.wiosnademo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "animals")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Animal {

  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "legs_num")
  private Integer legsNum;

  @Column(name = "typex")
  private String type;

  @Embedded
  private Hair hair;
}
