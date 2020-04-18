package pl.sdacademy.wiosnademo.configuration;

import javax.validation.constraints.AssertTrue;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ConfigurationProperties(prefix = "pl.sdacademy.users")
@EnableConfigurationProperties(UsersConfiguration.class)
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated // na starcie aplikacji zostanie sprawdzona poprawność danych
public class UsersConfiguration {

  private static final String NO_OP_PREFIX = "{noop}";
  private static final int MIN_PASSWORD_LENGTH = 8;

  private String userA;
  private String passwordA;

  private String userB;
  private String passwordB;

  private String userC;
  private String passwordC;

  @AssertTrue(message = "user A data is incorrect")
  public boolean isUserADataValid() {
    if(userA == null) {
      return false;
    }

    if (userA.length() < 2) {
      return false;
    }

    if (passwordA == null) {
      return false;
    }

    if (!passwordA.startsWith(NO_OP_PREFIX) || passwordA.length() < MIN_PASSWORD_LENGTH + NO_OP_PREFIX.length()) {
      return false;
    }

    if (passwordA.toLowerCase().equals(passwordA) || passwordA.toUpperCase().equals(passwordA)) {
      return false;
    }
    // TODO: check if contains a digit
    return true;
  }
}
