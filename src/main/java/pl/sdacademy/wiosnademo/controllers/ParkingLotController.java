package pl.sdacademy.wiosnademo.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pl.sdacademy.wiosnademo.domain.ParkingDetails;
import pl.sdacademy.wiosnademo.domain.ParkingLot;
import pl.sdacademy.wiosnademo.services.ParkingLotDetailsService;
import pl.sdacademy.wiosnademo.services.ParkingLotService;

@RestController
@RequestMapping(path = "/api/parking-lots")
// wymagana jedna z ról aby dostać się do jakiegokolwiek endpointy w tym kontrolerze
@Secured({"ROLE_READ", "ROLE_ADD_OR_MODIFY"})
public class ParkingLotController {

  private final ParkingLotService parkingLotService;
  private final ParkingLotDetailsService parkingLotDetailsService;

  public ParkingLotController(final ParkingLotService parkingLotService,
                              final ParkingLotDetailsService parkingLotDetailsService) {
    this.parkingLotService = parkingLotService;
    this.parkingLotDetailsService = parkingLotDetailsService;
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

  @PostMapping("/{id}/details")
  @ResponseStatus(HttpStatus.CREATED)
  public ParkingLot addDetailsToParking(@RequestBody @Valid final ParkingDetails parkingDetails,
                                        @PathVariable final Long id) {
    return parkingLotDetailsService.addDetailsToParking(id, parkingDetails);
  }
}
