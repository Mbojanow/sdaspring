package pl.sdacademy.wiosnademo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.wiosnademo.model.AnimalDto;
import pl.sdacademy.wiosnademo.model.Animals;
import pl.sdacademy.wiosnademo.services.AnimalService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/animals")
public class AnimalController {

  private final AnimalService animalService;

  public AnimalController(final AnimalService animalService) {
    this.animalService = animalService;
  }

  @GetMapping
  public Animals getAllAnimals() {
    return new Animals(animalService.getAllAnimals());
  }

  @GetMapping("/{id}")
  public AnimalDto getAnimalById(@PathVariable("id") final Long id) {
    return animalService.getById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public AnimalDto createAnimal(@Valid @RequestBody final AnimalDto animalDto) {
    return animalService.createAnimal(animalDto);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public AnimalDto updateAnimal(@Valid @RequestBody final AnimalDto animalDto, @PathVariable final Long id) {
    return animalService.updateAnimal(id, animalDto);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteAnimal(@PathVariable final Long id) {
    animalService.delete(id);
  }

}
