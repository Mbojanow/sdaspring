package pl.sdacademy.wiosnademo.domain;


import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

  private Long id;

  @JsonProperty("consignor")
  private String from;

  @JsonProperty("recipent")
  private String to;
  private String value;
  private LocalDateTime sendDate;

  @JsonIgnore
  public boolean isInFuture() {
    return sendDate.isAfter(LocalDateTime.now());
  }
}
