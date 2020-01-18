package pl.sdacademy.wiosnademo.domain;

import static java.util.Objects.isNull;

import java.util.stream.Stream;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "parking_lot_details")
public class ParkingLotDetails {
  @Id
  @GeneratedValue
  private Long id;

  @NotNull
  @Length(min = 2)
  @Column(name = "manager_name")
  private String managerName;

  @Column(name = "mobile_number")
  private String mobileNumber;

  @Null
  @JsonIgnore
  @OneToOne
  @JoinColumn(name = "parking_id")
  private ParkingLot parkingLot;

  @JsonIgnore
  @AssertTrue
  public boolean isMobileNumberValid() {
    if (isNull(mobileNumber)) {
      return false;
    }
    final String[] splitMobileNumber = mobileNumber.split("-");
    if (splitMobileNumber.length != 3) {
      return false;
    }

    final boolean partsLengthValid = Stream.of(splitMobileNumber)
        .map(String::chars)
        .allMatch(numberPart -> numberPart.count() == 3);

    return partsLengthValid && mobileNumber.length() == 11 && Stream.of(splitMobileNumber)
        .map(String::chars)
        .allMatch(x -> x.allMatch(Character::isDigit));

//        .map(String::chars)
//        .allMatch(x -> x.count() == 3 && x.allMatch(ch -> Character.isDigit(ch))) &&
//        mobileNumber.length() == 11;
  }
}
