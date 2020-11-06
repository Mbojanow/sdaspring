package pl.sdacademy.wiosnademo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User {

  @Id
  @NotNull(message = "username is mandatory")
  @Length(min = 3, message = "username needs to have at least 3 characters")
  private String username;

  @Column(name = "email")
  @UniqueEmail
  private String email;

  @Enumerated(EnumType.STRING)
  @Column(name = "user_type")
  private UserType type;

  @Column(name = "created_at")
  @CreationTimestamp
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  @UpdateTimestamp
  private LocalDateTime updatedAt;
}
