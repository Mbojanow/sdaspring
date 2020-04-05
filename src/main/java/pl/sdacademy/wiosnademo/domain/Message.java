package pl.sdacademy.wiosnademo.domain;


import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

  @Null(message = "id should not be provided")
  private Long id;

  @NotNull(message = "consignor is a mandatory field")
  @Length(min = 3)
  @JsonProperty("consignor")
  private String from;


  @JsonProperty("recipent")
  private String to;

  @Length(min = 1, message = "message text cannot be empty")
  @NotNull
  private String value;

  private LocalDateTime sendDate;

  @JsonIgnore
  public boolean isInFuture() {
    return sendDate.isAfter(LocalDateTime.now());
  }
}
