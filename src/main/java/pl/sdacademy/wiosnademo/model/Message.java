package pl.sdacademy.wiosnademo.model;

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

  @Null(message = "Id should not be provided")
  private Long id;

  @NotNull
  @Length(min = 3, message = "consignor length must be at least 3")
  @JsonProperty("consignor")
  private String from;

  @NotNull
  @Length(min = 3)
  @JsonProperty("recipent")
  private String to;

  @NotNull
  @Length(min = 1)
  private String value;

  private LocalDateTime sendDate = LocalDateTime.now();

  @JsonIgnore
  public boolean isInFuture() {
    return sendDate.isAfter(LocalDateTime.now());
  }
}
