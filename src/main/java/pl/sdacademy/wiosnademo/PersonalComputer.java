package pl.sdacademy.wiosnademo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "pcs")
public class PersonalComputer {

  @Id
  private String name;

  @Column(name = "installed_os")
  private String installedOs;

  @Column(name = "processor_name")
  private String processorName;

  @Column(name = "core_speed")
  private Double coreSpeed;

  @Column(name = "disc_space")
  private Integer discSpace;
}
