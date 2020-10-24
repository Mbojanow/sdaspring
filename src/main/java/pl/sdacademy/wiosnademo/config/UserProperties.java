package pl.sdacademy.wiosnademo.config;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sdacademy.wiosnademo.model.UserStatus;

@Validated
@Data
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "sda.user")
@Component
//@EnableConfigurationProperties(UserProperties.class)
public class UserProperties {

  @NotNull(message = "email has to be provided")
  @Email
  private String email;

  @Length(min = 8)
  @NotNull
  private String password;
  private UserStatus status;
}
