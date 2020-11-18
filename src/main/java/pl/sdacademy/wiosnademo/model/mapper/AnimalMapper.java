package pl.sdacademy.wiosnademo.model.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.sdacademy.wiosnademo.domain.Animal;
import pl.sdacademy.wiosnademo.model.AnimalDto;

@Mapper
public interface AnimalMapper {

  @Mapping(target = "hair", ignore = true)
  @Mapping(target = "hair.has", source = "hasHair")
  @Mapping(target = "hair.length", source = "hairLength")
  @Mapping(target = "hair.color", source = "hairColor")
  Animal animalDtoToAnimal(AnimalDto animalDto);

  @InheritInverseConfiguration
  AnimalDto animalToAnimalDto(Animal animal);


}
