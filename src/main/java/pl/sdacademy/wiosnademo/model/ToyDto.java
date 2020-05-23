package pl.sdacademy.wiosnademo.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ToyDto {

  private Long id;

  @NotNull
  private String name;

  @JsonProperty("material")
  private String material;
  private String producer;
  private Long age;
}
