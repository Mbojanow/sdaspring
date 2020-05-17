package pl.sdacademy.wiosnademo.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pl.sdacademy.wiosnademo.domain.ParkingLot;
import pl.sdacademy.wiosnademo.services.ParkingLotService;

@RestController
@RequestMapping("/api/parking-lots") //kebab-case
public class ParkingLotController {

  private final ParkingLotService parkingLotService;

  public ParkingLotController(final ParkingLotService parkingLotService) {
    this.parkingLotService = parkingLotService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ParkingLot createParking(@Valid @RequestBody final ParkingLot parkingLot) {
    return parkingLotService.createParkingLot(parkingLot);
  }

  @GetMapping
  // poprawić w czasie sesji samodzielnej
  public List<ParkingLot> getAllParking() {
    return parkingLotService.findAllParkings();
  }

  @GetMapping("/{id}")
  public ParkingLot findParking(@PathVariable("id") final Long id) {
    return parkingLotService.findParkingById(id);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void updateParking(@Valid @RequestBody final ParkingLot parkingLot, @PathVariable final Long id) {
    parkingLotService.updateParking(id, parkingLot);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable final Long id) {
    parkingLotService.deleteById(id);
  }

  // PatchMapping ? co z walidacja?

  // Dodać prosty exception handling

  // poprawić serializacje i deserializacje JSONow -> snake case zamiast camel case, co z null?
}
