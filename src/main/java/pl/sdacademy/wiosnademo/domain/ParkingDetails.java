package pl.sdacademy.wiosnademo.domain;

import java.util.stream.Stream;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "parking_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "parkingLot")
public class ParkingDetails {

  @Id
  @GeneratedValue
  private Long id;

  @NotNull
  @Length(min = 2)
  @Column(name = "manager_name")
  private String managerName;

  @Column(name = "phone_number")
  private String phoneNumber;

  @JsonIgnore // no more stack overflow error
  @OneToOne
  @JoinColumn(name = "parking_id")
  private ParkingLot parkingLot;

  @AssertTrue
  public boolean isPhoneNumberValid() {
    if (phoneNumber == null) {
      return false;
    }

    final String[] splitPhoneNumber = phoneNumber.split("-");
    if (splitPhoneNumber.length != 3) {
      return false;
    }

    final boolean parseLengthValid = Stream.of(splitPhoneNumber)
        .map(String::chars)
        .allMatch(number -> number.count() == 3);

    if (!parseLengthValid) {
      return false;
    }

    return Stream.of(splitPhoneNumber)
        .map(String::chars)
        .allMatch(number -> number.allMatch(Character::isDigit));

  }

}
