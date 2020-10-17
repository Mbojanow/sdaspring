package pl.sdacademy.wiosnademo.domain;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Entity(name = "shops")
@Table(name = "shops")
public class Shop {

  /*• name niepusty, wymagany
• adres – niepusty, wymagany, w formacie Ulica Nr
• area – dodatni, pole opcjonalne
• phoneNumber w formacie xxx-xxx-xxx
   */

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Length(min = 1, message = "name has to be not empty",
      groups = {ValidationGroups.FullValidation.class,
          ValidationGroups.PartialValidation.class})
  @NotNull(message = "name is a mandatory field",
      groups = {ValidationGroups.FullValidation.class})
  @Column(name = "name")
  private String name;

  @Column(name = "address")
  private String address;

  @Min(value = 1, message = "area has to be positive number",
    groups = {ValidationGroups.FullValidation.class,
        ValidationGroups.PartialValidation.class})
  @Column(name = "area")
  private Integer area;

  @Column(name = "phone_number")
  private String phoneNumber;

  @JsonIgnore
  @AssertTrue(message = "address is mandatory",
      groups = ValidationGroups.FullValidation.class)
  public boolean isAddressProvided() {
    return address != null;
  }

  @JsonIgnore
  @AssertTrue(message = "address has invalid format",
  groups = {ValidationGroups.FullValidation.class,
            ValidationGroups.PartialValidation.class})
  public boolean isAddressValid() {
    if (address == null) {
      return true;
    }

    final String[] splitAddress = address.split(" ");
    if (splitAddress.length != 2) {
      return false;
    }
    return splitAddress[1].chars()
        .allMatch(Character::isDigit);
  }

  @JsonIgnore
  @AssertTrue(message = "phone has invalid format")
  public boolean isPhoneValidAndIDontKnowHowToRegex() {
    if (phoneNumber == null) {
      return true;
    }

    final String[] splitPhone = phoneNumber.split("-");
    if (splitPhone.length != 3) {
      return false;
    }
    return Arrays.stream(splitPhone)
        .allMatch(substr -> substr.chars()
            .allMatch(Character::isDigit));
  }
}
