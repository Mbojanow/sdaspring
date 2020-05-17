package pl.sdacademy.wiosnademo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.sdacademy.wiosnademo.domain.ParkingLot;
import pl.sdacademy.wiosnademo.services.ParkingLotService;

@RestController
@RequestMapping("/api/parking-lots/search")
public class ParkingSearchController {

  private final ParkingLotService parkingLotService;

  public ParkingSearchController(final ParkingLotService parkingLotService) {
    this.parkingLotService = parkingLotService;
  }

  @GetMapping
  public ParkingLot searchByName(@RequestParam(name = "parking-name") final String name) {
    return parkingLotService.getParkingByName(name);
  }
}
