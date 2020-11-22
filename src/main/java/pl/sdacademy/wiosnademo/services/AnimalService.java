package pl.sdacademy.wiosnademo.services;

import static java.util.Objects.isNull;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.sdacademy.wiosnademo.aspects.EntryExitLogger;
import pl.sdacademy.wiosnademo.domain.Animal;
import pl.sdacademy.wiosnademo.exception.SdaException;
import pl.sdacademy.wiosnademo.model.AnimalDto;
import pl.sdacademy.wiosnademo.model.mapper.AnimalMapper;
import pl.sdacademy.wiosnademo.repository.AnimalRepository;

@Transactional
@Service
public class AnimalService {

  private final AnimalRepository animalRepository;
  private final AnimalMapper animalMapper;

  public AnimalService(final AnimalRepository animalRepository, final AnimalMapper animalMapper) {
    this.animalRepository = animalRepository;
    this.animalMapper = animalMapper;
  }

  public AnimalDto getById(final Long id) {
    final Animal animal = animalRepository.getById(id);
    if (isNull(animal)) {
      throw new SdaException("Animal with given id does not exist", HttpStatus.NOT_FOUND.value());
    }
    return animalMapper.animalToAnimalDto(animal);
  }

  @EntryExitLogger
  public List<AnimalDto> getAllAnimals() {
    return getAllAnimalsPrivate();
  }

  private List<AnimalDto> getAllAnimalsPrivate() {
    return animalRepository.getAll().stream()
        .map(animalMapper::animalToAnimalDto)
        .collect(Collectors.toList());
  }

  public AnimalDto createAnimal(final AnimalDto animalDto) {
    final Animal animalToSave = animalMapper.animalDtoToAnimal(animalDto);
    final Animal savedAnimal = animalRepository.create(animalToSave);
    return animalMapper.animalToAnimalDto(savedAnimal);
  }

  public AnimalDto updateAnimal(final Long id, final AnimalDto animalDto) {
    final Animal existingAnimal = animalRepository.getById(id);
    if (isNull(existingAnimal)) {
      throw new SdaException("Cannot update non existing animal", HttpStatus.NOT_FOUND.value());
    }
    final Animal updatedAnimal = animalMapper.animalDtoToAnimal(animalDto);
    updatedAnimal.setId(id);
    return animalMapper.animalToAnimalDto(animalRepository.update(updatedAnimal));
  }

  public void delete(final Long id) {
    final Animal existingAnimal = animalRepository.getById(id);
    if (isNull(existingAnimal)) {
      throw new SdaException("Cannot delete non existing animal", HttpStatus.NOT_FOUND.value());
    }
    animalRepository.delete(id);
  }
}
