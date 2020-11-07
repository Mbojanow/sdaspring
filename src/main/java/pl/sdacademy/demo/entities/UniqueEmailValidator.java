package pl.sdacademy.demo.entities;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;
import pl.sdacademy.demo.repositories.UserRepository;

@Component
@NoArgsConstructor // ten konstruktor wykorzystuje hibernate
public class UniqueEmailValidator implements ConstraintValidator<UniqueMail, String> {

  @Autowired
  private UserRepository userRepository; // brak final, bo hibernate nie ma możliwośći wstrzykiwania komponentów

  @Override
  public boolean isValid(final String value, final ConstraintValidatorContext context) {
    if (userRepository == null) { //pominięcie walidacji na poziomie hibernate (przy persist, merge)
      return true;
    }
    return userRepository.findByEmail(value).isEmpty(); // walidacja przy wykorzystaniu @Valid w springu
  }
}
