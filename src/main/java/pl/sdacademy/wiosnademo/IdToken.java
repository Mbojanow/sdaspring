package pl.sdacademy.wiosnademo;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "id_tokens")
public class IdToken {

  @Id
  private String sub;
  private String iss;
  private Long iat;
  private Long exp;
}
