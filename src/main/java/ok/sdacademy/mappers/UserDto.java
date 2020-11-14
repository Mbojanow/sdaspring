package ok.sdacademy.mappers;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

  @JsonProperty("mail")
  @NotNull
  private String mail;

  @Length(min = 3)
  private String nick;

  private List<GroupDto> groups;
}
