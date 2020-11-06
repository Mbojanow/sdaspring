package pl.sdacademy.wiosnademo;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@NoArgsConstructor
@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

  @Autowired
  private UserRepository userRepository;

  @Override
  public void initialize(final UniqueEmail constraintAnnotation) {
  }

  @Override
  public boolean isValid(final String value, final ConstraintValidatorContext context) {
    if (userRepository != null) {
      return userRepository.findByEmail(value).isEmpty();
    }
    return true;
  }
}
