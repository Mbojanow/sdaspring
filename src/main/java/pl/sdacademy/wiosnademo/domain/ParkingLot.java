package pl.sdacademy.wiosnademo.domain;

import java.util.stream.Stream;

import javax.persistence.CascadeType;
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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "parking_lots")
public class ParkingLot {

  //Walidacja: name jest wymagany o długości minimum 5,
  // Adres musi podany i składać się z nazwy ulicy i numeru ulicy, // Baczynskiego 15, Baczynskiego
  // places musi być dodatnie

  @Id
  @GeneratedValue
  private Long id;

  @NotNull
  @Length(min = 5)
  private String name;

  private String address;

  @Min(1)
  private Long places;

  @JsonIgnore
  @AssertTrue
  public boolean isAddressValid() {
    if (address == null) {
      return false;
    }

    final String[] splitAddress = address.split(" ");
    if (splitAddress.length != 2) {
      return false;
    }

    //Stream [ [] ]
    return splitAddress[1].chars() // IntStream -> Stream -> allMatch
        .allMatch(Character::isDigit); // x -> A.Y(x) == A::Y
  }

  @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
  @JoinColumn(name = "details_id")
  private ParkingDetails parkingDetails;

}
