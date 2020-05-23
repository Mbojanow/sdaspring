package pl.sdacademy.wiosnademo.configuration;

import java.util.stream.Stream;

import javax.validation.constraints.AssertTrue;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Validated
@ConfigurationProperties(prefix = "pl.sda.security")
//@Component - nowy sposób
@EnableConfigurationProperties(UsersConfig.class) // po staremu
@Configuration                                    // po staremu
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersConfig {

  private String userA;
  private String passwordA;
  private String userB;
  private String passwordB;
  private String userC;
  private String passwordC;

  @AssertTrue
  public boolean isUsersConfigValid() {
    return Stream.of(userA, userB, userC)
        .allMatch(user -> user.length() > 2);
  }

  @AssertTrue
  public boolean isPasswordValid() {
    return Stream.of(passwordA, passwordB, passwordC)
      .allMatch(password -> password.length() >= 8
          && !password.equals(password.toLowerCase())
          && !password.equals(password.toUpperCase())
          && password.chars().anyMatch(Character::isDigit));
  }
}
