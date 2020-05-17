package pl.sdacademy.wiosnademo.model;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NameForm {

  @Length(min = 1)
  private String firstName;

  @Length(min = 2)
  private String lastName;
}
