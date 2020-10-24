package pl.sdacademy.wiosnademo.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "roles")
public class Role {

  @Id
  @Length(min = 3)
  @Column(name = "name")
  private String name;

  @NotNull
  @Length(min = 3)
  @Column(name = "description")
  private String description;

  @NotNull
  @Column(name = "enabled")
  private Boolean enabled;

  @ManyToMany(mappedBy = "roles")
  private List<User> users;
}
