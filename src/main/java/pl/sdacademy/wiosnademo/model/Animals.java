package pl.sdacademy.wiosnademo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Animals {
  private List<AnimalDto> animals;
}
