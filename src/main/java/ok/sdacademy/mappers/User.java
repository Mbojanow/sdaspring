package ok.sdacademy.mappers;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User {
  @Id
  private Long id;

  private String email;

  private String username;

  private String password;

  @OneToMany
  @JoinColumn(name = "user_id")
  private List<Group> groups;
}
