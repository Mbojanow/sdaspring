package pl.sdacademy.wiosnademo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalDto {

  @JsonProperty("id")
  private Long id;

  @NotNull
  @JsonProperty("name")
  private String name;

  @NotNull
  @JsonProperty("legs_num")
  private Integer legsNum;

  @NotNull
  @JsonProperty("type")
  private String type;

  @NotNull
  @JsonProperty("has_hair")
  private Boolean hasHair;

  @NotNull
  @JsonProperty("hair_length")
  private Long hairLength;

  @NotNull
  @JsonProperty("hair_color")
  private String hairColor;
}
