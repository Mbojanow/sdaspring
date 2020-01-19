package pl.sdacademy.wiosnademo.configuration;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.util.HashSet;
import java.util.List;
import java.util.function.IntPredicate;

import javax.validation.constraints.AssertTrue;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ConfigurationProperties(prefix = "sda.users")
@Configuration
@Validated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersConfiguration {

 private List<String> usernames;
 private List<String> passwords;

 @AssertTrue
 public boolean isUsersConfigCorrect() {
   return nonNull(usernames) && nonNull(passwords) &&
       usernames.size() == passwords.size() &&
       new HashSet<>(usernames).size() == usernames.size() &&
       usernames.stream().allMatch(user -> nonNull(user) && user.length() >= 2) &&
       passwords.stream().allMatch(this::isPasswordCorrect);
 }

  public boolean isPasswordCorrect(final String password) {
    if (isNull(password)) {
      return false;
    }
    return password.length() >= 8 && passwordASatisfiesPredicate(password, Character::isDigit) &&
        passwordASatisfiesPredicate(password, Character::isUpperCase) &&
        passwordASatisfiesPredicate(password, Character::isLowerCase);
  }

  private boolean passwordASatisfiesPredicate(final String password, final IntPredicate predicate) {
    return password.chars().anyMatch(predicate);
  }
}
