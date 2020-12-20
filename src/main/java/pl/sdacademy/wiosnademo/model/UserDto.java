package pl.sdacademy.wiosnademo.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

  private Long id;

  private String mail;

  private String nick;

  private List<GroupDto> groups;
}
