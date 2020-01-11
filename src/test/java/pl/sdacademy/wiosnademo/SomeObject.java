package pl.sdacademy.wiosnademo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SomeObject {

  private Long id;

  @JsonProperty("some_value")
  private String someValue;

  @JsonIgnore
  public boolean isIdEven() {
    return id % 2 == 0;
  }

  @JsonIgnore
  public String getSth() {
    return "sth";
  }
}
