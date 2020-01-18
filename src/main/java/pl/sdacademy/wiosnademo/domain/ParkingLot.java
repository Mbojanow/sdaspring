package pl.sdacademy.wiosnademo.domain;

import java.util.stream.Stream;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "parking_lots")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ParkingLot {

  //Walidacja: name jest wymagany o długości minimum 5,
  // Adres musi podany i składać się z nazwy ulicy i numeru ulicy,
  // places musi być dodatnie

  @Id
  @GeneratedValue
  private Long id;

  @NotNull
  @Length(min = 5)
  @Column(name = "parking_name")
  private String name;

  @Column(name = "address")
  private String address;

  @Min(1)
  @Column(name = "places")
  private Long places;

  @OneToOne
  @JoinColumn(name = "details_id")
  private ParkingLotDetails details;

  @AssertTrue
  @JsonIgnore
  public boolean isAddressValid() {
    if (address == null) {
      return false;
    }
    final String[] splitAddress = address.split(" ");
    if (splitAddress.length < 1) {
      return false;
    }
    return splitAddress[splitAddress.length - 1].chars()
        .allMatch(Character::isDigit) && address.length() == address.trim().length();
  }
}
