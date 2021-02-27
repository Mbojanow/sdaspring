package pl.sdacademy.wiosnademo.domain;

import java.time.LocalDateTime;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "users")
public class User {

  @Id
  @Length(min = 3)
  private String username;

  @NotNull
  @Email
  private String email;

  @NotNull
  private String password;

  @JsonProperty("mobile")
  private String phoneNumber;

  @JsonIgnore
  @CreationTimestamp
  private LocalDateTime creationDate;

  @JsonIgnore
  @UpdateTimestamp
  private LocalDateTime updateDate;

  @JsonIgnore
  @AssertTrue // nazwa metody musi zaczynać się od is
  public boolean isPhoneNumberValid() {
    if (phoneNumber == null) {
      return true;
    }
    //+XX XXX XXX XXX lub XXX XXX XXX
    final String[] splitData = phoneNumber.split(" ");
    if (splitData.length == 3) {
      final boolean allElementsHaveSameExpectedSize = Arrays.stream(splitData).allMatch(subPart -> subPart.length() == 3);
      if (!allElementsHaveSameExpectedSize) {
        return false;
      }

      return Arrays.stream(splitData)
          .allMatch(subPart -> subPart.chars()
              .allMatch(Character::isDigit));
    } else if (splitData.length == 4) {
      // IMPLEMENT VALIDATION
    }
    return false;
  }
}
