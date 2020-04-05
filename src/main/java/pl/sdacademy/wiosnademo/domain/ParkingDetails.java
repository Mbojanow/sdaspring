package pl.sdacademy.wiosnademo.domain;

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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "parking_details")
public class ParkingDetails {

  @Id
  @GeneratedValue
  private Long id; // UUID

  @NotNull
  @Length(min = 2)
  @Column(name = "manager_name")
  private String managerName;

  @Column(name = "phone_number")
  private String phoneNumber;

  @JsonIgnore
  @OneToOne
  @JoinColumn(name = "parking_id")
  private ParkingLot parkingLot;

  @AssertTrue
  public boolean isPhoneNumberValid() {
    if (phoneNumber == null) {
      return false;
    }
    final String[] split = phoneNumber.split("-");
    if (split.length != 3) {
      return false;
    }
    //xxx-xxx-xxx
    boolean isValid = true;
    for (int idx = 0; idx < 3; idx++) {
      if (split[idx].length() != 3) {
        return false;
      }
      isValid = isValid && split[idx].chars().allMatch(Character::isDigit);
    }
    return isValid;
  }
}
