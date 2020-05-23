package pl.sdacademy.wiosnademo.configuration;

import java.util.Map;
import java.util.stream.Stream;

import javax.validation.constraints.AssertTrue;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Validated
@ConfigurationProperties(prefix = "pl.sdacademy.security")
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersConfigOnMap {
  private Map<String, String> credentials;

  @AssertTrue
  public boolean isUsersConfigValid() {
    return credentials.keySet().stream()
        .allMatch(user -> user.length() > 2);
  }

  @AssertTrue
  public boolean isPasswordValid() {
    return credentials.values().stream()
        .allMatch(password -> password.length() >= 8
            && !password.equals(password.toLowerCase())
            && !password.equals(password.toUpperCase())
            && password.chars().anyMatch(Character::isDigit));
  }
}
