package pl.sdacademy.wiosnademo.model;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserForm {

  @NotNull
  @Email
  private String email;

  @Length(min = 8)
  private String password;
  private String confirmPassword;

  @AssertTrue
  public boolean isPasswordValid() {
    if (password == null || !password.equals(confirmPassword)) {
      return false;
    }

    return !password.toLowerCase().equals(password) &&
        password.chars().anyMatch(ch -> Character.isAlphabetic(ch)) &&
        password.chars().anyMatch(ch -> Character.isDigit(ch));

  }
}
