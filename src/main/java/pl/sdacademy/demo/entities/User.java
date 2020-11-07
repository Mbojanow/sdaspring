package pl.sdacademy.demo.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "users") // dane w tabeli users I odwołujemy do encji user jako users - zapytania HQL
public class User {

  @Id
  @Length(min = 3)
  private String username;

  @Email
  @NotNull
  @UniqueMail
  private String email;

  @Enumerated(EnumType.STRING)
  private UserType type;

  @CreationTimestamp
  private LocalDateTime createdAt;

  @UpdateTimestamp
  private LocalDateTime updatedAd;
}
