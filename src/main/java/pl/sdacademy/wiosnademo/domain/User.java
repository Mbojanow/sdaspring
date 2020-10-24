package pl.sdacademy.wiosnademo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sdacademy.wiosnademo.model.UserStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User {

  @Id
  @Email
  @Column(name = "email")
  private String email;

  @NotNull
  @Column(name = "password")
  private String password;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private UserStatus status;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "users_to_roles",
    joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "email"),
    inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "name")) // definicja dla Role
  private List<Role> roles = new ArrayList<>();
}
