package pl.sdacademy.wiosnademo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "messages_counter")
public class MessagesCounter {
  @Id
  @GeneratedValue
  private Long id;

  private Long counter;
}
