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
@RequestMapping(path = "/api/parking-lots")
public class ParkingLotController {

  private final ParkingLotService parkingLotService;

  public ParkingLotController(final ParkingLotService parkingLotService) {
    this.parkingLotService = parkingLotService;
  }

  @GetMapping
  public List<ParkingLot> getAllParkingLots() {
    return parkingLotService.getAll();
  }

  @GetMapping("/{id}")
  public ParkingLot getParking(@PathVariable/*(name = "id")*/ final Long id) {
    return parkingLotService.getById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ParkingLot createParking(@RequestBody @Valid final ParkingLot parkingLot) {
    return parkingLotService.createParking(parkingLot);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void updateParking(@PathVariable final Long id, @RequestBody final ParkingLot parkingLot) {
    parkingLotService.update(id, parkingLot);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteParkingLot(@PathVariable final Long id) {
    parkingLotService.deleteParking(id);
  }
}
