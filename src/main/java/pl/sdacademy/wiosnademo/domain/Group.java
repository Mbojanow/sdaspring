package pl.sdacademy.wiosnademo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "groups")
public class Group {

  @Id
  @NotNull
  private String name;

  @NotNull
  @Enumerated(EnumType.STRING)
  private GroupType type;

  //@ManyToMany(fetch = FetchType.EAGER)
  @ManyToMany
  @JoinTable(name = "groups_to_users",
    joinColumns = @JoinColumn(name = "group_id", referencedColumnName = "name"),
    inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "username"))
  private List<User> users = new ArrayList<>();
}
