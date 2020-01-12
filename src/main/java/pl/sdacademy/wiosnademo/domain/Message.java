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

  @Null(message = "id must not be provided")
  private Long id;

  @NotNull(message = "consignor field has to be provided")
  @Length(min = 3, message = "consignor field's length has to be at least 3")
  @JsonProperty("consignor")
  private String from;

  @NotNull(message = "recipent field is mandatory")
  @Length(min = 3, message = "recipent len has to be at least 3")
  @JsonProperty("recipent")
  private String to;

  @NotNull(message = "message value is mandatory")
  @Length(min = 1, message = "message value cannot be empty")
  private String value;
  private LocalDateTime sendDate;

  @JsonIgnore
  public boolean isInFuture() {
    return sendDate.isAfter(LocalDateTime.now());
  }
}
