package pl.sdacademy.wiosnademo.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

  private Long id;
  private String from;
  private String to;
  private LocalDateTime sendDate;

  public boolean isInFuture() {
    return sendDate.isAfter(LocalDateTime.now());
  }
}
