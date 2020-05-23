package pl.sdacademy.wiosnademo.model;

import org.mapstruct.Mapper;

import pl.sdacademy.wiosnademo.domain.Toy;

@Mapper
public interface ToyMapper {

  Toy toyDtoToToy(ToyDto toyDto);

  ToyDto toyToToyDto(Toy toy);
}
