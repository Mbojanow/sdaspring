package pl.sdacademy.wiosnademo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "parking_lots")
public class ParkingLot {

  @Id
  @GeneratedValue
  private Long id;

  @NotNull
  @Length(min = 5)
  @Column(name = "name")
  private String name;

  @Column(name = "address")
  private String address;

  @Min(1)
  @Column(name = "places")
  private Integer places;

  @AssertTrue
  public boolean isAddressValid() {
    if (address == null) {
      return false;
    }

    final String[] splitAddress = address.split(" ");
    if (splitAddress.length < 2) {
      return false;
    }

    return splitAddress[1].chars()
        .allMatch(Character::isDigit);
  }
}
