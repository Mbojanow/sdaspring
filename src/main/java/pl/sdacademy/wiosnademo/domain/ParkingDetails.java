package pl.sdacademy.wiosnademo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "parking_details")
public class ParkingDetails {

  @Id
  @GeneratedValue
  private Long id; // UUID

  @Column(name = "manager_name")
  private String managerName;

  @Column(name = "phone_number")
  private String phoneNumber;

  @OneToOne
  @JoinColumn(name = "parking_id")
  private ParkingLot parkingLot;
}
