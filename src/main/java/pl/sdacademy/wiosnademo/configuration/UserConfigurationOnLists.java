package pl.sdacademy.wiosnademo.configuration;

import java.util.List;

import javax.validation.constraints.AssertTrue;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ConfigurationProperties(prefix = "pl.sdacademy.users-list")
@EnableConfigurationProperties(UserConfigurationOnLists.class)
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class UserConfigurationOnLists {

  private List<String> usernames;
  private List<String> passwords;

  @AssertTrue
  public boolean isUsersDataValid() {
    if (usernames == null || passwords == null) {
      return false;
    }

    if (usernames.size() != passwords.size()) {
      //log.warn("Users and passwords length mismatch");
      return false;
    }

    if (usernames.stream().allMatch(this::isUsernameValid) &&
        passwords.stream().allMatch(this::isPasswordValid)) {
      return true;
    }
    return false;
  }

  private boolean isUsernameValid(final String username) {
    return username.length() >= 2;
  }

  private boolean isPasswordValid(final String password) {
    //14 hasło musi miec 8 znaków + {noop} ma 6 znaków
    return !password.toUpperCase().equals(password) && password.toLowerCase().equals(password) && password.length() > 14;
  }
}
