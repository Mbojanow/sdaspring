package pl.sdacademy.wiosnademo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "parking_lots")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ParkingLot {

  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "parking_name")
  private String name;

  @Column(name = "address")
  private String address;

  @Column(name = "places")
  private Long places;
}
