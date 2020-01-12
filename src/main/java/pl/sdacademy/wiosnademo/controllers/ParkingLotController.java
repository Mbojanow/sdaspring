package pl.sdacademy.wiosnademo.controllers;

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
import pl.sdacademy.wiosnademo.model.ParkingLots;
import pl.sdacademy.wiosnademo.services.ParkingLotService;

@RestController // @Controller + @ResponseBody
@RequestMapping("/api/parking-lots")
public class ParkingLotController {

  private final ParkingLotService parkingLotService;

  public ParkingLotController(final ParkingLotService parkingLotService) {
    this.parkingLotService = parkingLotService;
  }

  @GetMapping
  public ParkingLots getAll() {
    return new ParkingLots(parkingLotService.getAll());
  }

  @GetMapping("/{id}")
  public ParkingLot getById(@PathVariable final Long id) {
    return parkingLotService.findById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ParkingLot create(@Valid @RequestBody final ParkingLot parkingLot) {
    return parkingLotService.create(parkingLot);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void update(@PathVariable final Long id, @Valid @RequestBody final ParkingLot parkingLot) {
    parkingLotService.update(id, parkingLot);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable final Long id) {
    parkingLotService.deleteById(id);
  }
}
