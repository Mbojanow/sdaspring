package pl.sdacademy.wiosnademo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "groups")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Group {

  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "name")
  private String name;
}
